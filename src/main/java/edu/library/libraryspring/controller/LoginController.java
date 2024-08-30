package edu.library.libraryspring.controller;

import edu.library.libraryspring.dto.MemberDTO;
import edu.library.libraryspring.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    private final MemberService ms;

    @GetMapping("/login")
    protected void loginGET(Model model, HttpServletRequest req) {

        log.info("GET member login ......................");

        // Find remember-me cookie
        Cookie rememberCookie = findCookie(req.getCookies(), "remember-me");

        if (rememberCookie != null && !rememberCookie.getValue().isEmpty()) {
            // If remember-me exists
            String m_uuid = rememberCookie.getValue();
            log.info("remember-me cookie found: " + m_uuid);

            try {
                // Retrieve user information by UUID and add it to the model
                MemberDTO memDTO = ms.getByUUID(m_uuid);

                if (memDTO != null) {
                    model.addAttribute("m_id", memDTO.getM_id());
                }
            } catch (Exception e) {
                log.error("Error retrieving member by UUID: ", e);
            }
        }
    }

    @PostMapping("/login")
    public String loginPOST(HttpServletRequest req, HttpServletResponse resp) {

        log.info("POST member login ......................");

        String m_id = req.getParameter("m_id");
        String m_pw = req.getParameter("m_pw");
        String m_uuid = req.getParameter("m_uuid");

        boolean rememberMe = m_uuid != null && m_uuid.equals("on");

        try {
            MemberDTO memDTO = ms.verify(m_id, m_pw);
            HttpSession session = req.getSession();

            if(memDTO.getM_no() == 1) {
                session.setAttribute("adminLogin", memDTO);
            }
            else {
                session.setAttribute("userLogin", memDTO);

                // Handle rememberMe (assign UUID)
                if (rememberMe) {
                    String uuid = UUID.randomUUID().toString();
                    log.info("Random uuid: " + uuid);

                    ms.setUuid(m_id, uuid);
                    memDTO.setM_uuid(uuid);
                    log.info("MemberDTO: " + memDTO);

                    Cookie rememberCookie = new Cookie("remember-me", uuid);
                    rememberCookie.setPath("/");
                    rememberCookie.setMaxAge(60*60*24*7); // Valid for one week

                    resp.addCookie(rememberCookie);
                }
            }
            session.setAttribute("m_name", memDTO.getM_name());

            log.info("-------------------------------------");
            log.info("AdminLogin: " + session.getAttribute("adminLogin"));
            log.info("UserLogin: " + session.getAttribute("userLogin"));

            return "redirect:/";
        } catch (Exception e) {
            log.info("Login fail ......................");
            return "redirect:/member/login?result=error";
        }
    }

    @GetMapping("/logout")
    public String logoutGET(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        log.info("GET member logout......................");

        HttpSession session = req.getSession();

        session.removeAttribute("adminLogin");
        session.removeAttribute("userLogin");

        session.invalidate();

        return "redirect:/";
    }

    /*
    @PostMapping("/logout")
    public String logoutPOST(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        log.info("POST member logout......................");

        HttpSession session = req.getSession();

        session.removeAttribute("userLogin");
        session.invalidate();

        return "redirect:/book/list";
    }
     */

    @GetMapping("/register")
    protected void registerGET(Model model) {

        log.info("GET member register ......................");
    }

    @PostMapping("/register")
    public String registerPOST(@Valid MemberDTO memDTO,
                               BindingResult bindingResult,
                               HttpServletRequest req,
                               HttpServletResponse resp,
                               RedirectAttributes redirectAttributes) throws IOException {

        log.info("POST member register...................");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        memDTO.setM_name(firstName + " " + lastName);

        if(bindingResult.hasErrors()) {
            log.info("Has errors......................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/member/register";
        }

        /*
        String phone = req.getParameter("m_phone");
        String id = req.getParameter("m_id");
        String pw = req.getParameter("m_pw");
        String confirmPw = req.getParameter("confirm");

        if(ms.verifyPhone(phone) == 0) {
            redirectAttributes.addFlashAttribute("phoneError", "OCCUR");
            return "redirect:/member/register";
        }
        if(ms.verifyId(id) == 0) {
            redirectAttributes.addFlashAttribute("idError", "OCCUR");
            return "redirect:/member/register";
        }
        if(isValidPw(pw, confirmPw, redirectAttributes) == false) {
            return "redirect:/member/register";
        }
         */

        log.info("memDTO: " + memDTO);
        ms.register(memDTO);

        return "redirect:/member/registerConfirmed";
    }

    private boolean isValidPw(String pw, String confirmedPw, RedirectAttributes redirectAttributes) {

        log.info("Password validation.................");

        // If they're not equal
        if (pw.equals(confirmedPw)) {
            redirectAttributes.addFlashAttribute("pwError", "1");
            return false;
        }

        if (pw.length() < 8) {
            redirectAttributes.addFlashAttribute("pwError", "2");
            return false;
        }

        int count = 0;
        if (pw.matches(".*[A-Z].*")) count++;
        if (pw.matches(".*[a-z].*")) count++;
        if (pw.matches(".*\\d.*")) count++;
        if (pw.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) count++;

        if(count >=3 == false) {
            redirectAttributes.addFlashAttribute("pwError", "3");
        }
        return count >= 3;
    }

    @GetMapping("/registerConfirmed")
    public void registerConfirmedGET(Model model) {

        log.info("GET member registerConfirmed......................");
    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) {

        Cookie targetCookie = null;

        if(cookies != null && cookies.length > 0) {
            for(Cookie ck: cookies) {
                if(ck.getName().equals(cookieName)) {
                    targetCookie = ck;
                    break;
                }
            }
        }

        if(targetCookie == null) {
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }
        return targetCookie;
    }
}

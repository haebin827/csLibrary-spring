package edu.library.libraryspring.controller;

import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/book")
@Log4j2
@RequiredArgsConstructor
public class BookController {

    private final BookService bs;

    @RequestMapping("/list")
    public void list(Model model) {

        log.info("book list......................");
        model.addAttribute("dtoList", bs.getAll());

    }

    @GetMapping("/register")
    public void registerGET() {

        log.info("GET Book register......................");
    }

    @PostMapping("/register")
    public String registerPOST(@Valid BookDTO bookDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("POST book register......................");

        if(bindingResult.hasErrors()) {
            log.info("Has errors......................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/book/register";
        }

        log.info("BookDTO: " + bookDTO);
        bs.register(bookDTO);

        return "redirect:/book/list";
    }

    @GetMapping({"/read", "/modify"})
    public void readGET(Long b_no, PageRequestDTO pageRequestDTO, Model model) {

        log.info("GET book read/modify......................");

        BookDTO bookDTO = bs.getOne(b_no);
        log.info("BookDTO: " + bookDTO);

        model.addAttribute("dto", bookDTO);
    }

    @PostMapping("/remove")
    public String removePOST(Long b_no, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("POST book remove......................");
        log.info("b_no: " + b_no);

        bs.remove(b_no);

        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/book/list?" + pageRequestDTO.getLink();
    }

    @PostMapping("/modify")
    public String modifyPOST(PageRequestDTO pageRequestDTO,
                         @Valid BookDTO bookDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        log.info("POST book modify......................");

        if(bindingResult.hasErrors()) {
            log.info("Has errors................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("b_no", bookDTO.getB_no());

            return "redirect:/book/modify";
        }

        log.info("BookDTO: " + bookDTO);
        bs.modify(bookDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        //redirectAttributes.addAttribute("b_no", bookDTO.getB_no());

        return "redirect:/book/list";
    }

    @GetMapping("/list")
    public void listGET(@Valid PageRequestDTO pageRequestDTO,
                        BindingResult bindingResult,
                        Model model) {

        log.info("GET book list......................");
        log.info("PageRequestDTO: " + pageRequestDTO);

        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", bs.getList(pageRequestDTO));
    }

    @GetMapping("/request")
    public String requestGET(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("GET book request......................");

        HttpSession session = req.getSession();

        if(session.isNew()) {
            log.info("JSESSIONID: User with active session");
            return "redirect:/member/login";
        }

        if(session.getAttribute("userLogin") == null) {
            log.info("JSESSIONID: User without login info");
            return "redirect:/member/login";
        }

        return "book/request";
    }
}

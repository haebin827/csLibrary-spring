package edu.library.libraryspring.controller;

import edu.library.libraryspring.dto.PageRequestDTO;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService ms;

    /*
    @GetMapping({"/read", "/modify"})
    public void readGET(String m_id, PageRequestDTO pageRequestDTO, Model model) {

        log.info("GET member read...................");

        MemberDTO memDTO = ms.getOne(m_id);
        log.info(memDTO);

        model.addAttribute("dto", memDTO);
    }
     */

    @PostMapping("/remove")
    public String removePOST(String m_id, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {

        log.info("POST member remove...................");

        log.info("m_id: " + m_id);
        ms.remove(m_id);

        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/member/list";
    }

    @GetMapping("/list")
    public void listGET(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {

        log.info("GET member list...................");
        log.info("PageRequestDTO: " + pageRequestDTO);

        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", ms.getList(pageRequestDTO));
    }
}

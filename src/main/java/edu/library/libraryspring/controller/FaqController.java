package edu.library.libraryspring.controller;

import edu.library.libraryspring.dto.FaqDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/faq")
@Log4j2
@RequiredArgsConstructor
public class FaqController {

    private final FaqService fs;

    @RequestMapping("/list")
    public void list(Model model) {

        log.info("faq list......................");
        model.addAttribute("dtoList", fs.getAll());
    }

    @GetMapping("/register")
    public void registerGET() {

        log.info("GET faq register......................");
    }

    @PostMapping("/register")
    public String registerPOST(@Valid FaqDTO faqDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("POST faq register...................");

        if(bindingResult.hasErrors()) {
            log.info("Has errors......................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/faq/register";
        }

        log.info(faqDTO);
        fs.register(faqDTO);

        return "redirect:/faq/list";
    }

    @GetMapping({"/read", "/modify"})
    public void readGET(Long f_id, PageRequestDTO pageRequestDTO, Model model) {

        log.info("GET faq read/modify......................");

        FaqDTO faqDTO = fs.getOne(f_id);
        log.info(faqDTO);

        model.addAttribute("dto", faqDTO);
    }

    @PostMapping("/remove")
    public String removePOST(Long f_id, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {

        log.info("POST faq remove......................");

        log.info("f_id: " + f_id);
        fs.remove(f_id);

        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/faq/list";
    }

    @PostMapping("/modify")
    public String modifyPOST(PageRequestDTO pageRequestDTO,
                         @Valid FaqDTO faqDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        log.info("POST faq modify......................");

        if(bindingResult.hasErrors()) {
            log.info("Has errors......................");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("f_id", faqDTO.getF_id());

            return "redirect:/faq/modify";
        }

        log.info(faqDTO);
        fs.modify(faqDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/faq/list";
    }

    @GetMapping("/list")
    public void listGET(@Valid PageRequestDTO pageRequestDTO,
                        BindingResult bindingResult,
                        Model model) {

        log.info("POST faq list......................");
        log.info("PageRequestDTO: " + pageRequestDTO);

        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", fs.getList(pageRequestDTO));
    }
}

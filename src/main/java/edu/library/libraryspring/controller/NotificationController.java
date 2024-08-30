package edu.library.libraryspring.controller;

import edu.library.libraryspring.dto.NotificationDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.service.NotificationService;
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
@RequestMapping("/notif")
@Log4j2
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService ns;

    @RequestMapping("/list")
    public void list(Model model) {

        log.info("notify list...................");
        model.addAttribute("dtoList", ns.getAll());
    }

    @GetMapping("/register")
    public void registerGET() {

        log.info("GET notify register...................");
    }

    @PostMapping("/register")
    public String registerPOST(@Valid NotificationDTO notifDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("POST notify register...................");

        if(bindingResult.hasErrors()) {
            log.info("Has errors................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/notif/register";
        }

        log.info(notifDTO);
        ns.register(notifDTO);

        return "redirect:/notif/list";
    }

    @GetMapping({"/read", "/modify"})
    public void readGET(Long n_no, PageRequestDTO pageRequestDTO, Model model) {

        log.info("GET notify read/modify...................");

        NotificationDTO notifDTO = ns.getOne(n_no);
        log.info(notifDTO);

        model.addAttribute("dto", notifDTO);
    }

    @PostMapping("/remove")
    public String removePOST(Long n_no, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {

        log.info("POST notify remove...................");
        log.info("n_no: " + n_no);

        ns.remove(n_no);

        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/notif/list";
    }

    @GetMapping("/list")
    public void listGET(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("GET notify list...................");
        log.info("PageRequestDTO: " + pageRequestDTO);

        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", ns.getList(pageRequestDTO));
    }

    @PostMapping("/modify")
    public String modifyPOST(PageRequestDTO pageRequestDTO,
                         @Valid NotificationDTO notifDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        log.info("POST member modify...................");
        log.info("notifDTO: " + notifDTO);

        if(bindingResult.hasErrors()) {
            log.info("Has errors................");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("n_no", notifDTO.getN_no());

            return "redirect:/notif/modify";
        }

        log.info(notifDTO);
        ns.modify(notifDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/notif/list";
    }
}

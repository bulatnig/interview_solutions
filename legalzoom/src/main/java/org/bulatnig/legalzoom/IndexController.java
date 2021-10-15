package org.bulatnig.legalzoom;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private volatile List<BankDetail> bankDetails = new ArrayList<>();

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("bankDetails", bankDetails);
        return "index";
    }

    @PostMapping("/upload")
    public String submit(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));
        List<BankDetail> newBankDetails = new ArrayList<>();
        reader.readNext(); // skip header
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            newBankDetails.add(new BankDetail(nextLine[0], nextLine[1], nextLine[2]));
        }
        this.bankDetails = newBankDetails;
        return "redirect:/";
    }

}

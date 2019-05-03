package magus.pi.picturewall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class IndexController {

    @Value("${store}")
    String store;

    File dir;

    @PostConstruct
    public void init() {
        dir = new File(store);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @GetMapping("/index")
    public String index(Model model) {
        //read images from the directory
        List<String>urls= Arrays.stream(dir.list())
                .map(x->"/image/"+x)
                .filter(x->true)
                .collect(Collectors.toList());
        model.addAttribute("urls", urls);
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("picture") MultipartFile mfile) {
        System.out.println("receive a picture");

        File file = new File(store + mfile.getOriginalFilename());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(mfile.getBytes());
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }
}

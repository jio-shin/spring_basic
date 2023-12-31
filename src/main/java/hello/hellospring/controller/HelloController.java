package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
    return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //http바디 부분에 리턴값을 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello "+ name;
        // name 에 spring 작성하면 -> hello spring으로 내려감 view 없이!
        //소스코드 확인하면 hello spring만 보임
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {//꺼낼 때
            return name;
        }

        public void setName(String name) {//집어 넣을 때
            this.name = name;
        }
    }

}

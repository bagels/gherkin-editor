package io.bagelz.editor.controller;

import io.bagelz.editor.model.Pong;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by green3 on 23/3/17.
 */
@RestController
public class PingController {

    @RequestMapping("/ping")
    public Pong ping() {
        return new Pong("Pong!", (int) DateTime.now().getMillis());
    }
}

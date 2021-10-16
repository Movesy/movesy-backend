package com.movesy.movesybackend.controller;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class DiaryController {
    @GetMapping("/diary")
    public String diary() throws IOException {
        Path markdownPath = Path.of("README.md");
        Parser parser = Parser.builder().build();

        Node document = parser.parse(Files.readString(markdownPath, StandardCharsets.UTF_8));
        HtmlRenderer renderer = HtmlRenderer.builder()
                .build();


        return renderer.render(document);
    }
}

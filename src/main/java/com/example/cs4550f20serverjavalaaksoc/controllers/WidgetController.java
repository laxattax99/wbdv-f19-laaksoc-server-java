package com.example.cs4550f20serverjavalaaksoc.controllers;

import com.example.cs4550f20serverjavalaaksoc.models.Widget;
import com.example.cs4550f20serverjavalaaksoc.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {
    @Autowired
    WidgetService widgetService;

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(
            @PathVariable("tid") String topicId) {
        return widgetService.findWidgetsForTopic(topicId);
    }

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return widgetService.findAllWidgets();
    }

    @GetMapping("/api/widgets/{wid}")
    public Widget findWidgetById(
            @PathVariable("wid") Integer widgetId) {
        return widgetService.findWidgetById(widgetId);
    }

    @PostMapping("/api/topics/{topicId}/widgets")
    public Widget createWidget(
            @PathVariable("topicId") String topicId,
            @RequestBody Widget widget) {
        widget.setTopicId(topicId);
        return widgetService.createWidget(widget);
    }

    @DeleteMapping("/api/widgets/{wid}")
    public void deleteWidget(@PathVariable("wid") Integer widgetId) {
        widgetService.deleteWidget(widgetId);
    }

    @PutMapping("/api/widgets/{wid}")
    public Widget updateWidget(@PathVariable("wid") Integer widgetId, @RequestBody Widget newWidget) {
        return widgetService.updateWidget(widgetId, newWidget);
    }
}

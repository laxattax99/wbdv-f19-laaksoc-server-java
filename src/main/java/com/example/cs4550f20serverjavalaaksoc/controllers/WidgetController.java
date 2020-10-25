package com.example.cs4550f20serverjavalaaksoc.controllers;

import com.example.cs4550f20serverjavalaaksoc.models.Widget;
import com.example.cs4550f20serverjavalaaksoc.services.WidgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {
    WidgetService widgetService = new WidgetService();

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
            @PathVariable("wid") String widgetId) {
        return widgetService.findWidgetById(widgetId);
    }
    @PostMapping("/api/topics/{topicId}/widgets")
    public Widget createWidget(
            @PathVariable("topicId") String topicId,
            @RequestBody Widget widget) {
        widget.setTopicId(topicId);
        return widgetService.createWidget(topicId, widget);
    }
    @DeleteMapping("/api/widgets/{wid}")
    public Integer deleteWidget(@PathVariable("wid") String widgetId) {
        return widgetService.deleteWidget(widgetId);
    }
    @PutMapping("/api/widgets/{wid}")
    public Integer updateWidget(@PathVariable("wid") String widgetId, @RequestBody Widget newWidget) {
        return widgetService.updateWidget(widgetId, newWidget);
    }
}

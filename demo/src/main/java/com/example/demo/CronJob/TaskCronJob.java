//package com.example.demo.CronJob;
//
//import com.example.demo.Domain.Task;
//import com.example.demo.util.TaskStatus;
//import com.example.demo.Service.TaskService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class TaskCronJob {
//
//    private TaskService taskService;
//
//    @Autowired
//    public TaskCronJob(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @Scheduled(cron = "0/20 * * * * ?")
//    public void checkTheOutDatedTasks() throws ParseException {
//        List<Task> tasks = taskService.findAllTasks();
//        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
//        LocalDateTime now = LocalDateTime.now();
//        Date today = sdformat.parse(String.valueOf(now));
//        for (Task task : tasks) {
//            if ((task.getTaskStatus().equals(TaskStatus.Assigned) || task.getTaskStatus().equals(TaskStatus.UnAssign)) && sdformat.format(task.getDeadline()).compareTo(String.valueOf(today)) < 0) {
//
//                task.setTaskStatus(TaskStatus.Outdated);
//                taskService.addTask(task);
//
//            }
//        }
//
//    }
//}

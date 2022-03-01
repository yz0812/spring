package com.yz.flowable.controller;

import liquibase.pro.packaged.R;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.DenyAll;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * created with IntelliJ IDEA.
 *
 * @author: yz
 * @date: 2022/2/22
 * @time: 9:51 下午
 * @description:
 */
@RestController
@AllArgsConstructor
public class ProcessesController {

    private final TaskService taskService;
    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final ProcessEngine processEngine;

    /**
     * . 提交采购订单的审批请求
     *
     * @param userId 用户 id
     */
    @PostMapping("/start/{userId}")
    public String startFlow(@PathVariable String userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("directorId", userId);
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("purchasingApproval", map);
        String processId = processInstance.getId();
        String name = processInstance.getName();
        System.out.println(processId + ":" + name);
        return processId + ":" + name;
    }

    /**
     * . 获取用户的任务
     *
     * @param userId 用户 id
     */
    @GetMapping("/getTasks/{userId}")
    public String getTasks(@PathVariable String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        return tasks.toString();
    }

    /**
     * 主管审批通过
     */
    @PostMapping("/managerSuccess/{managerId}/{taskId}")
    public String managerSuccess(@PathVariable String managerId,@PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return "流程不存在";
        }
        // 通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("executeType", true);
        map.put("managerId", managerId);
        taskService.complete(taskId, map);
        return "流程审核通过！";
    }

    /**
     * 主管审批拒绝
     */
    @PostMapping("/managerFaile/{managerId}/{taskId}")
    public String managerFaile(@PathVariable String managerId,@PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return "流程不存在";
        }
        // 通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("executeType", false);
       // map.put("managerId", managerId);
        taskService.complete(taskId, map);
        return "流程审核拒绝！";
    }



    /**
     * 经理审批通过
     */
    @PostMapping("/success/{managerId}/{taskId}")
    public String success(@PathVariable String managerId,@PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return "流程不存在";
        }
        // 通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("executeType", true);
        map.put("managerId", managerId);
        taskService.complete(taskId, map);
        return "流程审核通过！";
    }

    /**
     * 经理审批拒绝
     */
    @PostMapping("/faile/{managerId}/{taskId}")
    public String faile(@PathVariable String managerId,@PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return "流程不存在";
        }
        // 通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("executeType", false);
      //  map.put("managerId", managerId);
        taskService.complete(taskId, map);
        return "流程审核拒绝！";
    }

    @RequestMapping(value = "processDiagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        // 流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        // 使用流程实例 ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();
        // 得到正在执行的 Activity 的 Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }
        // 获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0,true);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}

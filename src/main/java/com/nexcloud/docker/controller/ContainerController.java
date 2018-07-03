package com.nexcloud.docker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nexcloud.docker.container.stats.domain.History;
import com.nexcloud.docker.resource.ResourceLoader;
import com.nexcloud.docker.service.ContainerService;

@Controller
public class ContainerController {

	@Autowired
	ContainerService con_svc;

	@RequestMapping(value = "/check")
	@ResponseBody
	public String check() {
		return "health check";
	}

	@RequestMapping("/")
    public ModelAndView index( Model model ) throws Exception {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("v1/common/layout");
		
		model.addAttribute("system_info", ResourceLoader.getInstance().getResource("system_info"));
		model.addAttribute("con_list", ResourceLoader.getInstance().getResource("con_list"));
		model.addAttribute("body", "v1/index");
		
		return view;
    }
	
	@RequestMapping("/containers/{container_id}/detail")
    public ModelAndView containerDetail( @PathVariable String container_id, Model model ) throws Exception {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("v1/common/layout");
		
		History history = (History)ResourceLoader.getInstance().getResource("history_"+container_id);
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		model.addAttribute("stat", gson.toJson(history));
		model.addAttribute("container_id", container_id);
		model.addAttribute("con", con_svc.getContainerInspect(container_id));
		model.addAttribute("body", "v1/container/detail");
		
		return view;
    }	
	
	@RequestMapping("/containers/{container_id}/stat")
    public ModelAndView containerStat( @PathVariable String container_id, Model model ) throws Exception {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("v1/container/stat");
		
		History history = (History)ResourceLoader.getInstance().getResource("history_"+container_id);
		Gson gson = new GsonBuilder().serializeNulls().create();
		model.addAttribute("stat", gson.toJson(history));
		model.addAttribute("container_id", container_id);

		return view;
    }	
	
	@RequestMapping("/containers/{container_id}/proc")
    public ModelAndView containerProc( @PathVariable String container_id, Model model ) throws Exception {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("v1/container/proc");
		
		model.addAttribute("proc", con_svc.getContainerProcess(container_id));
		model.addAttribute("container_id", container_id);

		return view;
    }	
	
	@RequestMapping("/containers/{container_id}/net")
    public ModelAndView containerNet( @PathVariable String container_id, Model model ) throws Exception {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("v1/container/net");
		model.addAttribute("net", con_svc.getContainerInspect(container_id));
		model.addAttribute("container_id", container_id);
		return view;
    }	
	
	@RequestMapping("/containers/{container_id}/env")
    public ModelAndView containerEnv( @PathVariable String container_id, Model model ) throws Exception {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("v1/container/env");
		model.addAttribute("env", con_svc.getContainerInspect(container_id));
		return view;
    }	
	
	@RequestMapping("/containers/{container_id}/vol")
    public ModelAndView containerVol( @PathVariable String container_id, Model model ) throws Exception {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("v1/container/vol");
		model.addAttribute("vol", con_svc.getContainerInspect(container_id));
		return view;
    }	
	
	@RequestMapping("/containers/{container_id}/log")
    public ModelAndView containerLog( @PathVariable String container_id, Model model ) throws Exception {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("v1/container/log");
		model.addAttribute("con_log", con_svc.getContainerLog(container_id));
		return view;
    }	
	
	@RequestMapping("/containers/{container_id}/realtime_stat")
	@ResponseBody
	public ModelMap statHistory( @PathVariable String container_id, HttpServletRequest request, Model model ) throws Exception {
		String time = request.getParameter("time");
		return con_svc.getStatHistory( container_id, time );
    }
	
	@RequestMapping("/containers/{container_id}/realtime_log")
	@ResponseBody
	public ModelMap logHistory( @PathVariable String container_id, HttpServletRequest request, Model model ) throws Exception {
		String time = request.getParameter("time");
		return con_svc.getContainerLogHistory( container_id, time );
    }
	
	@RequestMapping("/containers/container_list")
	@ResponseBody
	public ModelAndView containerList(HttpServletRequest request, Model model) throws Exception {		
		ModelAndView view = new ModelAndView();
		view.setViewName("v1/docker_list");
		model.addAttribute("con_list", con_svc.getContainerList(request));
		
		return view;
    }
	
}
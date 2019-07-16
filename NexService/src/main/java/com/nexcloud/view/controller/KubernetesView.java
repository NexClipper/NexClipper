/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.nexcloud.view.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/kubernetes")
public class KubernetesView {
	static final Logger logger = LoggerFactory.getLogger(KubernetesView.class);

	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboard() {
		return "kubernetes/dashboard";
	}
	
	@RequestMapping(value="/cluster", method=RequestMethod.GET)
	public String cluster() {
		return "kubernetes/cluster/view";
	}
	
	@RequestMapping(value="/cluster/detail", method=RequestMethod.GET)
	public String clusterDetail() {
		return "kubernetes/cluster/detail";
	}

	@RequestMapping(value="/node", method=RequestMethod.GET)
	public String node() {
		return "kubernetes/node/view";
	}
	
	@RequestMapping(value="/node/{nodeIp}/detail", method=RequestMethod.GET)
	public ModelAndView nodeDetail(@PathVariable(value="nodeIp", required=false) String nodeIp) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/node/detail");
		mav.addObject("nodeIp", nodeIp);
		return mav;
	}

	@RequestMapping(value="/workload", method=RequestMethod.GET)
	public ModelAndView workload(@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/workload/view");
		mav.addObject("namespace", namespace);
		return mav;
	}

	@RequestMapping(value="/daemonset", method=RequestMethod.GET)
	public ModelAndView daemonset(@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/daemonset/view");
		mav.addObject("namespace", namespace);
		return mav;
	}

	@RequestMapping(value="/daemonset/{daemonsetName}/detail", method=RequestMethod.GET)
	public ModelAndView daemonsetDetail(@PathVariable(value="daemonsetName", required=false) String daemonsetName,
		@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/daemonset/detail");
		mav.addObject("daemonsetName", daemonsetName);
		mav.addObject("namespace", namespace);
		return mav;
	}

	@RequestMapping(value="/deployment", method=RequestMethod.GET)
	public ModelAndView deployment(@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/deployment/view");
		mav.addObject("namespace", namespace);
		return mav;
	}
	@RequestMapping(value="/deployment/{deploymentName}/detail", method=RequestMethod.GET)
	public ModelAndView deploymentDetail(@PathVariable(value="deploymentName", required=false) String deploymentName,
		@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/deployment/detail");
		mav.addObject("deploymentName", deploymentName);
		mav.addObject("namespace", namespace);
		return mav;
	}

	@RequestMapping(value="/pod", method=RequestMethod.GET)
	public ModelAndView pod(@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/pod/view");
		mav.addObject("namespace", namespace);
		return mav;
	}
	
	@RequestMapping(value="/pod/{podName}/detail", method=RequestMethod.GET)
	public ModelAndView podDetail(@PathVariable(value="podName", required=false) String podName,
		@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/pod/detail");
		mav.addObject("podName", podName);
		mav.addObject("namespace", namespace);
		return mav;
	}
	
	@RequestMapping(value="/replicaset", method=RequestMethod.GET)
	public ModelAndView replicaset(@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/replicaset/view");
		mav.addObject("namespace", namespace);
		return mav;
	}

	@RequestMapping(value="/replicaset/{replicasetName}/detail", method=RequestMethod.GET)
	public ModelAndView replicasetDetail(@PathVariable(value="replicasetName", required=false) String replicasetName,
		@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/replicaset/detail");
		mav.addObject("replicasetName", replicasetName);
		mav.addObject("namespace", namespace);
		return mav;
	}
	
	@RequestMapping(value="/statefulset", method=RequestMethod.GET)
	public ModelAndView statefulset(@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/statefulset/view");
		mav.addObject("namespace", namespace);
		return mav;
	}

	@RequestMapping(value="/statefulset/{statefulsetName}/detail", method=RequestMethod.GET)
	public ModelAndView statefulsetDetail(@PathVariable(value="statefulsetName", required=false) String statefulsetName,
		@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/statefulset/detail");
		mav.addObject("statefulsetName", statefulsetName);
		mav.addObject("namespace", namespace);
		return mav;
	}

	@RequestMapping(value="/services", method=RequestMethod.GET)
	public ModelAndView services(@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/services/view");
		mav.addObject("namespace", namespace);
		return mav;
	}

	@RequestMapping(value="/services/{serviceName}/detail", method=RequestMethod.GET)
	public ModelAndView servicesDetail(@PathVariable(value="serviceName", required=false) String serviceName,
		@RequestParam(value="namespace", defaultValue="all") String namespace) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kubernetes/services/detail");
		mav.addObject("serviceName", serviceName);
		mav.addObject("namespace", namespace);
		return mav;
	}
}

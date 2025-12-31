package com.docker_kubernetes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("docker")
public class DockerKuberneteTestController {

    @GetMapping("test")
    public String test(){
        return "Docker Kubernetes Test Successful";
    }
    @GetMapping("kubernetestest")
    public String DockerKubernetesTest(){
        return "Docker Image pushed to docker-hub and deployed into Kubernetes Test Successful";
    }
    @GetMapping("jenkins-docker-kubernetes-test")
    public String jenkinsDockerKubernetesTest(){
        return "Springboot - Git hub - Jenkins-  Docker Image pushed to docker-hub and deployed into Kubernetes  Success....!!! ";
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
 }

/*
Copyright 2019 NexClipper.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package nexagent

import (
	"fmt"
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"log"
)

func (s *NexAgent) SetupApiHandler() {
	gin.SetMode("release")
	router := gin.Default()

	config := cors.DefaultConfig()
	config.AllowOrigins = []string{"*"}
	config.AllowMethods = []string{"*"}
	config.AllowHeaders = []string{"*"}
	config.AllowCredentials = true

	router.Use(cors.New(config))

	v1 := router.Group("/api/v1")
	{
		v1.GET("/health", s.ApiHealth)
	}

	go func() {
		log.Printf("Rest API started at 0.0.0.0:%d\n", s.config.Agent.ApiPort)
		err := router.Run(fmt.Sprintf("0.0.0.0:%d", s.config.Agent.ApiPort))
		if err != nil {
			log.Printf("failed api handler: %v\n", err)
		}
	}()
}

func (s *NexAgent) ApiHealth(c *gin.Context) {
	if s.connected {
		c.JSON(200, gin.H{
			"status":  "ok",
			"message": "",
		})
	} else {
		c.JSON(200, gin.H{
			"status":  "bad",
			"message": "server connection failed",
		})
	}
}

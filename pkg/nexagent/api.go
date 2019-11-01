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

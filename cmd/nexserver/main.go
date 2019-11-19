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

package main

import (
	"fmt"
	"github.com/NexClipper/NexClipper/pkg/nexserver"
	"github.com/urfave/cli"
	"log"
	"os"
)

func initApp() *cli.App {
	app := cli.NewApp()
	app.Version = nexserver.NexServerVersion
	app.Name = nexserver.AppName
	app.Description = nexserver.AppDescription
	app.Flags = []cli.Flag{
		cli.StringFlag{
			Name:   "config, c",
			Usage:  "Config file path",
			EnvVar: "NEXSERVER_CONFIG_PATH",
			Value:  "",
		},
		cli.StringFlag{
			Name:   "server",
			Usage:  "Server bind address",
			EnvVar: "NEXSERVER_BIND_ADDRESS",
			Value:  "0.0.0.0",
		},
		cli.IntFlag{
			Name:   "agent",
			Usage:  "Listening port for NexAgent",
			EnvVar: "NEXSERVER_AGENT_PORT",
			Value:  18000,
		},
		cli.IntFlag{
			Name:   "api",
			Usage:  "Listening port for REST API",
			EnvVar: "NEXSERVER_API_PORT",
			Value:  18001,
		},
		cli.BoolFlag{
			Name:   "tls",
			Usage:  "Use TLS secure communication channel",
			EnvVar: "NEXSERVER_TLS_USE",
		},
		cli.StringFlag{
			Name:   "tls.key",
			Usage:  "Path of TLS key file",
			EnvVar: "NEXSERVER_TLS_KEY_PATH",
		},
		cli.StringFlag{
			Name:   "tls.cert",
			Usage:  "Path of TLS cert file",
			EnvVar: "NEXSERVER_TLS_CERT_PATH",
		},
		cli.StringFlag{
			Name:   "db.host",
			Usage:  "Database host address",
			EnvVar: "NEXSERVER_DB_HOST",
			Value:  "localhost",
		},
		cli.IntFlag{
			Name:   "db.port",
			Usage:  "Database host port",
			EnvVar: "NEXSERVER_DB_PORT",
			Value:  5432,
		},
		cli.StringFlag{
			Name:   "db.user",
			Usage:  "Database username",
			EnvVar: "NEXSERVER_DB_USER",
			Value:  "nexclipper",
		},
		cli.StringFlag{
			Name:   "db.pass",
			Usage:  "Database password",
			EnvVar: "NEXSERVER_DB_PASS",
			Value:  "",
		},
		cli.StringFlag{
			Name:   "db.name",
			Usage:  "Database name",
			EnvVar: "NEXSERVER_DB_NAME",
			Value:  "nexclipper",
		},
		cli.StringFlag{
			Name:   "db.sslmode",
			Usage:  "Use SSL for database connection (enable/disable)",
			EnvVar: "NEXSERVER_DB_SSLMODE",
			Value:  "disable",
		},
		cli.Float64Flag{
			Name:   "rule.node_cpu_load1",
			Usage:  "Basic incident rule for node's cpu load too high",
			EnvVar: "NEXSERVER_RULE_NODE_CPU_LOAD1",
			Value:  1.0,
		},
		cli.Float64Flag{
			Name:   "rule.node_disk_free",
			Usage:  "Basic incident rule for node's free space too small",
			EnvVar: "NEXSERVER_RULE_NODE_DISK_FREE",
			Value:  50.0,
		},
		cli.Float64Flag{
			Name:   "rule.node_memory_free",
			Usage:  "Basic incident rule for node's free memory too small",
			EnvVar: "NEXSERVER_RULE_NODE_MEMORY_FREE",
			Value:  90,
		},
	}

	app.Action = func(c *cli.Context) error {
		nexServer := nexserver.NewNexServer()
		configPath := c.String("config")

		if configPath != "" {
			err := nexServer.LoadConfig(configPath)
			if err != nil {
				return fmt.Errorf("failed to load config: %v\n", err)
			}
		} else {
			bindAddress := c.String("server")
			agentPort := c.Int("agent")
			apiPort := c.Int("api")

			nexServer.SetServerConfig(bindAddress, agentPort, apiPort)

			dbHost := c.String("db.host")
			dbPort := c.Int("db.port")
			dbUser := c.String("db.user")
			dbPass := c.String("db.pass")
			dbName := c.String("db.name")
			dbSslMode := c.String("db.sslmode")

			nexServer.SetDatabaseConfig(dbHost, dbPort, dbUser, dbPass, dbName, dbSslMode)

			ruleNodeLoad1 := c.Float64("rule.node_cpu_load1")
			ruleNodeDiskFree := c.Float64("rule.node_disk_free")
			ruleNodeMemoryFree := c.Float64("rule.node_memory_free")

			nexServer.SetBasicRule(ruleNodeLoad1, ruleNodeDiskFree, ruleNodeMemoryFree)
		}

		_, err := nexServer.ConnectDatabase()
		if err != nil {
			log.Fatalf("failed to database connect: %v\n", err)
		}

		if err := nexServer.Start(); err != nil {
			log.Fatalf("stopped server: %v\n", err)
		}

		return nil
	}

	return app
}

func main() {
	app := initApp()

	err := app.Run(os.Args)
	if err != nil {
		log.Fatal(err)
	}
}

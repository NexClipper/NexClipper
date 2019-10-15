package main

import (
	"github.com/NexClipper/nexclipper-engine/pkg/nexserver"
	"github.com/urfave/cli"
	"log"
	"os"
)

func main() {
	app := cli.NewApp()
	app.Version = nexserver.NexServerVersion
	app.Name = "migrate"
	app.Description = "Database Migration Tool for NexServer"
	app.Flags = []cli.Flag{
		cli.StringFlag{
			Name:     "db.host",
			Usage:    "Database host address",
			EnvVar:   "DB_HOST",
			Required: true,
			Value:    "localhost",
		},
		cli.IntFlag{
			Name:   "db.port",
			Usage:  "Database port number",
			EnvVar: "DB_PORT",
			Value:  5432,
		},
		cli.StringFlag{
			Name:     "db.user",
			Usage:    "Database user name",
			EnvVar:   "DB_USER",
			Required: true,
			Value:    "postgres",
		},
		cli.StringFlag{
			Name:   "db.pass",
			Usage:  "Database password",
			EnvVar: "DB_PASS",
			Value:  "",
		},
		cli.StringFlag{
			Name:     "db.name",
			Usage:    "Database name",
			EnvVar:   "DB_NAME",
			Required: true,
			Value:    "nexclipper",
		},
		cli.StringFlag{
			Name:   "db.sslmode",
			Usage:  "Use of SSL",
			EnvVar: "DB_SSLMODE",
			Value:  "disable",
		},
	}

	app.Action = func(c *cli.Context) error {
		err := nexserver.Migrate(
			c.String("db.host"), c.Int("db.port"),
			c.String("db.user"), c.String("db.pass"),
			c.String("db.name"), c.String("db.sslmode"))
		if err != nil {
			log.Printf("Migration failed: %v\n", err)
			return err
		}

		return nil
	}

	err := app.Run(os.Args)
	if err != nil {
		log.Fatal(err)
	}
}

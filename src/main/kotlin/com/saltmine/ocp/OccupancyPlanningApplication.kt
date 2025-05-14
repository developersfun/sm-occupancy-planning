package com.saltmine.ocp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OccupancyPlanningApplication

fun main(args: Array<String>) {
    runApplication<OccupancyPlanningApplication>(*args)
}

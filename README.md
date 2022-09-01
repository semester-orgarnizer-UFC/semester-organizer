# Semester organizer

It's really boring and stressing when you've to plan and decide what classes should we take and in which semester. To plan for just for only one semester (the next one) is really a mess, so you don't even imagine and don't even try to plan the remaining semesters.

That's why you can use this software to help you out with this planning.

## The objective

In this software you would be able: 

-  To populate your semesters with classes that you've done already and classes that you want to do in the next semesters. 

That's it! Very simple and straighfoward, we're planning also to study our data to collect some important informations: 

-  Which teacher is the most choosed for given class.
-  Which collection of classes is the most popular for the given semester of a given course.
-  Collect feedback of a given class of a given teacher, and then we can base our choices on it.

Regardless these tech objectives, the main goal of this project is to gather the most developers as possible and create a net around this open source project, if you're interested, please feel free to send me a massage. Also, take a look on the [how to contribute document](CONTRIBUTING.MD)

## How to run the project locally

Make sure you've the following setup installed:

- Docker and Docker compose

- JDK 11

Open your terminal, and type the following commands:

```console
git clone https://github.com/semester-orgarnizer-UFC/semester-organizer
```

And then, on the root folder, run:

```console
./run.sh
```

> If you have any problem, try to run with permissions (sudo on Linux distros)

To see if it works, at the end of the console output, check if the following pattern is matched:

```console
frontend    | You can now view frontend in the browser.
frontend    |
frontend    |   Local:            http://localhost:3000
frontend    |   On Your Network:  http://172.20.0.3:3000
```

Try to access http://localhost:3000

### Architeture

#### Backend

```shell
├── config // Configuration files
├── exceptions // The exceptions
├── model // The domain, the classes, the entities
├── repositories // The DB integration 
├── resources // The endpoints 
├── security // The security implementation
└── services // The business logic
```

### [How to contribute](CONTRIBUTING.MD)

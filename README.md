# Semester organizer 
The umbrella project of the application

# How to run the project locally 

Make sure you've the following setup installed: 

- Docker and Docker compose 
- JDK 11

Open your terminal, and type the following commands: 

```console
git clone --recurse-submodules https://github.com/semester-orgarnizer-UFC/semester-organizer 
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



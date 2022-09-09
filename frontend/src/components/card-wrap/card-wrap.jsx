import React, { useState, useEffect, useContext } from "react";
import { Card, CardContent, Typography } from "@mui/material";
import "./card-wrap.css";
import ClassCard from "./../class-card/class-card.jsx";
import { Box } from "@mui/material";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { SemesterContext } from "../../providers/semester-provider";
import { useDrop } from "react-dnd";
import { updateSemester } from "../../services/semester-service";
import useCustomSnackbar from "../snackbar/use-custom-snackbar.js";


export function CardWrap(props) {
  const { semester, setSemester } = useContext(SemesterContext);
  const [classes, setClasses] = useState();
  const snackbar = useCustomSnackbar();


  const [{ isOver }, drop] = useDrop(() => ({
    accept: "Card",
    drop: (item) => addClassesToTheSemester(item.id),
    collect: (monitor) => ({
      isOver: !!monitor.isOver(),
    }),
  }));

  const addClassesToTheSemester = (id) => {
    const semester = {
      index: props.index,
      classes: [
        {
          ref: "Classes",
          id: id,
        },
      ],
    };
    updateSemester(semester).then((res) => {
      if (res.status === 201) {
        setSemester(res.data.semester);
      } else {
        snackbar.showError(res.message);
      }
    });
  };

  useEffect(() => {
    if (props.data.classes) {
      setClasses(
        props.data.classes.map((item, index) => (
          <ClassCard
            name={item.name}
            id={item.id}
            hours={item.hours}
            key={index}
          ></ClassCard>
        ))
      );
    }
  }, [semester]);

  return (
    <ThemeProvider theme={theme}>
      <Card
        ref={drop}
        className="card-wrap"
        sx={{
          background: "var(--card)",
          overflowY: "scroll",
          opacity: isOver ? 0.7 : 1,
        }}
      >
        <CardContent>
          <Typography sx={{ mb: 1.5 }} color="primary">
            {props.index}º semestre
          </Typography>
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              gap: "10px",
            }}
          >
            {classes ? classes : <div></div>}
          </Box>
        </CardContent>
      </Card>
    </ThemeProvider>
  );
}
export default CardWrap;

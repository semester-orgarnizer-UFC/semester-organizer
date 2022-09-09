import React, { useState, useEffect, useContext } from "react";
import { Card, CardContent, Typography } from "@mui/material";
import "./card-wrap.css";
import ClassCard from "./../class-card/class-card.jsx";
import { Box } from "@mui/material";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { SemesterContext } from "../../providers/semester-provider";

export function CardWrap(props) {
  const { semester } = useContext(SemesterContext);
  const [classes, setClasses] = useState();

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
        className="card-wrap"
        sx={{
          background: "var(--card)",
          overflowY: "scroll",
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
            {classes ? classes : <p>teste</p>}
          </Box>
        </CardContent>
      </Card>
    </ThemeProvider>
  );
}
export default CardWrap;

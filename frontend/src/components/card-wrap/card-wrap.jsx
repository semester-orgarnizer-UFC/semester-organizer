import React from "react";
import { Card, CardContent, Typography } from "@mui/material";
import "./card-wrap.css";
import ClassCard from "./../class-card/class-card.jsx";
import { Box } from "@mui/material";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";

const data = [
  {
    name: "Fundamentos de programação",
    id: "QXD0001",
    hours: 64,
  },
  {
    name: "Programção orientada a objetos",
    id: "QXD0007",
    hours: 64,
  },
];

const listItems = data.map((item) => (
  <ClassCard name={item.name} id={item.id} hours={item.hours}></ClassCard>
));
export function CardWrap(props) {
  return (
    <ThemeProvider theme={theme}>
      <Card sx={{ minWidth: 275 }}>
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
            {listItems}
          </Box>
        </CardContent>
      </Card>
    </ThemeProvider>
  );
}
export default CardWrap;

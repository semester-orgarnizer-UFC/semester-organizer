import React from "react";
import { Card, CardContent, Typography } from "@mui/material";
import "./card-wrap.css";
import ClassCard from "./../class-card/class-card.jsx";
import { Box } from "@mui/material";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";

export function CardWrap(props) {

  const listItems = props.data.map((item, index) => (
    <ClassCard name={item.name} id={item.id} hours={item.hours} key={index}></ClassCard>
  ));
  return (
    <ThemeProvider theme={theme}>
      <Card
        className="card-wrap"
        sx={{
          background: "var(--card)",
          overflowY: 'scroll'
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
            {listItems}
          </Box>
        </CardContent>
      </Card>
    </ThemeProvider>
  );
}
export default CardWrap;

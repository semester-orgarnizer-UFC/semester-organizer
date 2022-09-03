import React from "react";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import AccordionDetails from "@mui/material/AccordionDetails";
import Typography from "@mui/material/Typography";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
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
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1a-content"
          id="panel1a-header"
        >
          <Typography color="primary">{props.index}º semestre</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "space-between",
              gap: "10px",
            }}
          >
            {listItems}
          </Box>
        </AccordionDetails>
      </Accordion>
    </ThemeProvider>
  );
}
export default CardWrap;

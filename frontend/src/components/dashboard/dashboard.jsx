import React from "react";
import "./dashboard.css";
import { Card, CardContent, Typography, Box, Container } from "@mui/material";
import CardWrap from "./../card-wrap/card-wrap.jsx";
import CssBaseline from "@mui/material/CssBaseline";

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
const Dashboard = () => (
  <Box className="dashboard">
    <Box
      sx={{
        display: "flex",
        gap: "10px",
      }}
    >
      <CardWrap index={1} data={data} />
      <CardWrap index={3} />
      <CardWrap index={4} />
      <CardWrap index={5} />
      <CardWrap index={6} />
      <CardWrap index={7} />
      <CardWrap index={8} />
    </Box>
  </Box>
);

export default Dashboard;

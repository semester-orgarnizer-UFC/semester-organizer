import React from "react";
import "./dashboard.css";
import { Card, CardContent, Typography, Box, Container } from "@mui/material";
import CardWrap from "./../card-wrap/card-wrap.jsx";

const Dashboard = () => (
  <Container
    sx={{
      display: "flex",
      justifyContent: "space-between",
      gap: "10px",
    }}
  >
    <CardWrap index={1} />
    <CardWrap index={2} />
  </Container>
);

export default Dashboard;

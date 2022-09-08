import React, { useContext } from "react";
import "./dashboard.css";
import { Box, Container, ThemeProvider, SpeedDialIcon } from "@mui/material";
import CardWrap from "./../card-wrap/card-wrap.jsx";
import ClassesNotTaken from "../classes-not-taken/classes-not-taken";
import { theme } from "./../../theme.js";
import BottomBar from "../BottomBar/BottomBar";
import AddSemester from "../add-semester/add-semester";
import { useState } from "react";
import { useEffect } from "react";
import { findAll } from "../../services/semester-service";
import { ClassesProvider } from "../../providers/classes-provider";
import DashboardWrap from "../dashboard-wrap/dashboard-wrap";
import { SemesterProvider } from "../../providers/semester-provider";
function Dashboard() {
 
  return (
    <SemesterProvider>
      <DashboardWrap>
        <ClassesProvider>
          <AddSemester />
          <ClassesNotTaken />
        </ClassesProvider>
        <BottomBar />
      </DashboardWrap>
    </SemesterProvider>
  );
}

export default Dashboard;

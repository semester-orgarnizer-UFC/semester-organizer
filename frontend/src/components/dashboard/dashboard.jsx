import React, { useContext } from "react";
import "./dashboard.css";
import ClassesNotTaken from "../classes-not-taken/classes-not-taken";
import BottomBar from "../BottomBar/BottomBar";
import AddSemester from "../add-semester/add-semester";
import { ClassesProvider } from "../../providers/classes-provider";
import DashboardWrap from "../dashboard-wrap/dashboard-wrap";
import { SemesterProvider } from "../../providers/semester-provider";
import { DndProvider } from "react-dnd";
import { HTML5Backend } from "react-dnd-html5-backend";
function Dashboard() {
  return (
    <SemesterProvider>
      <DndProvider backend={HTML5Backend}>
        <DashboardWrap>
          <ClassesProvider>
            <AddSemester />
            <ClassesNotTaken />
          </ClassesProvider>
          <BottomBar />
        </DashboardWrap>
      </DndProvider>
    </SemesterProvider>
  );
}

export default Dashboard;

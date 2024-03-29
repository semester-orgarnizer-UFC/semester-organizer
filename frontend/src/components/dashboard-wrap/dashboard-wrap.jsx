import React, { useContext, useEffect } from "react";
import "./dashboard-wrap.css";
import { Container } from "@mui/system";
import { SemesterContext } from "../../providers/semester-provider";
import CardWrap from "../card-wrap/card-wrap";
import { findAll } from "../../services/semester-service";

function DashboardWrap(props) {
  const { semester } = useContext(SemesterContext);
  
  return (
    <Container
      sx={{
        display: "flex",
        flexDirection: "row",
        alignItems: "start",
        gap: 2,
        overflowX: "scroll",
        height: "90vh",
      }}
    >
      {semester ? (
        semester.map((item) => {
          return (
            <CardWrap data={item} index={item.index} key={item.index}></CardWrap>
          );
        })
      ) : (
        <div></div>
      )}
      {props.children}
    </Container>
  );
}

DashboardWrap.propTypes = {};

DashboardWrap.defaultProps = {};

export default DashboardWrap;

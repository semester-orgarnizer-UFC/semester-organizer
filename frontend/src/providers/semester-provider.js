import React, { createContext, useEffect, useState } from "react";
import { findAll } from "../services/semester-service";

export const SemesterContext = createContext({});

export const SemesterProvider = (props) => {
  const [semester, setSemester] = useState();

  useEffect(() => {
    findAll().then((data) => {
      setSemester(data.data);
    });
  }, []);

  return (
    <SemesterContext.Provider value={{semester, setSemester}}>
      {props.children}
    </SemesterContext.Provider>
  );
};

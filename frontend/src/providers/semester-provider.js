import React, { createContext, useEffect, useState } from "react";
import { findNotTakenClasses } from "../services/user-service";

export const SemesterContext = createContext({});

export const SemesterProvider = (props) => {
  const [classes, setClasses] = useState();

  useEffect(() => {
    findNotTakenClasses().then((data) => {
      setClasses(data.data);
      console.log(classes);
    });
  }, []);

  return (
    <SemesterContext.Provider value={{classes, setClasses}}>
      {props.children}
    </SemesterContext.Provider>
  );
};

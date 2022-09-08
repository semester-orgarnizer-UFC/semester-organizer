import React, { createContext, useEffect, useState } from "react";
import { findNotTakenClasses } from "../services/user-service";

export const ClassesContext = createContext({});

export const SemesterProvider = (props) => {
  const [classes, setClasses] = useState();

  useEffect(() => {
    findNotTakenClasses().then((data) => {
      setClasses(data.data);
      console.log(classes);
    });
  }, []);

  return (
    <ClassesContext.Provider value={{classes, setClasses}}>
      {props.children}
    </ClassesContext.Provider>
  );
};

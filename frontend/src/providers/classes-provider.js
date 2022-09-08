import React, { createContext, useEffect, useState } from "react";
import { findNotTakenClasses } from "../services/user-service";

export const ClassesContext = createContext({});

export const ClassesProvider = (props) => {
  const [classes, setClasses] = useState();

  useEffect(() => {
    findNotTakenClasses().then((data) => {
      setClasses(data.data);
    });
  }, []);

  return (
    <ClassesContext.Provider value={{classes, setClasses}}>
      {props.children}
    </ClassesContext.Provider>
  );
};

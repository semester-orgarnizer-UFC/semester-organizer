import React, { useState, useEffect } from "react";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import {} from "@mui/material";
import {
  Card,
  CardContent,
  InputAdornment,
  Box,
  Input,
  Skeleton,
} from "@mui/material";
import "./classes-not-taken.css";
import ClassCard from "../class-card/class-card.jsx";
import SearchIcon from "@mui/icons-material/Search";
import { useContext } from "react";
import { ClassesContext } from "../../providers/classes-provider.js";
import { findNotTakenClasses } from "../../services/user-service.js";
import { SemesterContext } from "../../providers/semester-provider.js";


function ClassesNotTaken() {
  const [searchInput, setSearchInput] = useState("");
  const { classes, setClasses } = useContext(ClassesContext);
  const {semester} = useContext(SemesterContext);

  function getClassesNotTaken() {
    findNotTakenClasses().then((data) => {
      setClasses(data.data);
    });
  }

  useEffect(() => {
    getClassesNotTaken();
  }, [semester]);

  const searchItems = (event) => {
    if (event.target.value === "") {
      setClasses(getClassesNotTaken());
    }
    setSearchInput(event.target.value);
    const filteredItems = classes.filter(({ name }) =>
      name.toLowerCase().includes(searchInput.toLowerCase())
    );
    setClasses(filteredItems);
  };

  return (
    <ThemeProvider theme={theme}>
      <Card
        sx={{
          minWidth: 275,
          width: 350,
          background: "var(--card)",
          overflowY: "scroll",
          height: 500,
          maxHeight: 500,
        }}
      >
        <CardContent>
          <Input
            onChange={(e) => searchItems(e)}
            fullWidth
            variant="filled"
            color="primary"
            id="input-with-icon-adornment"
            placeholder="Pesquisar..."
            sx={{
              color: "var(--primary)",
            }}
            startAdornment={
              <InputAdornment position="start">
                <SearchIcon />
              </InputAdornment>
            }
          />
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              gap: "10px",
              mt: 2,
            }}
          >
            {classes ? (
              classes.map((item) => (
                <ClassCard
                  item={item}
                  key={item.id}
                ></ClassCard>
              ))
            ) : (
              <Skeleton variant="rectangular" width={275} height={600} />
            )}
          </Box>
        </CardContent>
      </Card>
    </ThemeProvider>
  );
}

ClassesNotTaken.propTypes = {};

ClassesNotTaken.defaultProps = {};

export default ClassesNotTaken;

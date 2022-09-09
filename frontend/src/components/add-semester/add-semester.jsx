import React from "react";
import PropTypes from "prop-types";
import "./add-semester.css";
import { Box, Card, Typography, CardContent } from "@mui/material";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import AddIcon from "@mui/icons-material/Add";
import { createEmptySemester } from "../../services/semester-service";
import { useContext } from "react";
import { ClassesContext } from "../../providers/classes-provider.js";
import { findNotTakenClasses } from "../../services/user-service.js";
import { SemesterContext } from "../../providers/semester-provider";
import useCustomSnackbar from "../snackbar/use-custom-snackbar";

function AddSemester() {
  const { setClasses } = useContext(ClassesContext);
  const { setSemester } = useContext(SemesterContext);
  const snackbar = useCustomSnackbar();

  const createNewSemester = async () => {
    await createEmptySemester().then((data) => {
      if (data.status == 201) {
        setSemester(data.data.semester);
      } else {
        snackbar.showError(data.message);
      }
    });
    await findNotTakenClasses().then((data) => {
      setClasses(data.data);
    });
  };

  return (
    <ThemeProvider theme={theme}>
      <Card
        onClick={createNewSemester}
        sx={{
          minWidth: 275,
          width: 350,
          background: "var(--card)",
          opacity: "0.7",
        }}
      >
        <CardContent>
          <Box className="box">
            <Typography color="primary" variant="h5">
              Adicionar semestre
            </Typography>
            <AddIcon color="secondary" />
          </Box>
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              gap: "10px",
            }}
          ></Box>
        </CardContent>
      </Card>
    </ThemeProvider>
  );
}

AddSemester.propTypes = {};

AddSemester.defaultProps = {};

export default AddSemester;

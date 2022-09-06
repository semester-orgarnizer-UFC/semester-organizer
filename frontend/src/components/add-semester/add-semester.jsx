import React from "react";
import PropTypes from "prop-types";
import "./add-semester.css";
import { Box, Card, Typography, CardContent } from "@mui/material";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import AddIcon from "@mui/icons-material/Add";
import { createOrUpdateSemester } from "../../services/semester-service";

const createNewSemester = () => {
  createOrUpdateSemester();
};

const AddSemester = () => (
  <ThemeProvider theme={theme}>
    <Card
    onClick={createNewSemester}
      sx={{
        minWidth: 275,
        width: 350,
        background: "var(--card)",
        opacity: '0.7'
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

AddSemester.propTypes = {};

AddSemester.defaultProps = {};

export default AddSemester;

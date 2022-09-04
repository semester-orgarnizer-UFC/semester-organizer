import React from "react";
import PropTypes from "prop-types";
import "./add-semester.css";
import { Box, Card, Typography, CardContent } from "@mui/material";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import AddIcon from "@mui/icons-material/Add";

const createNewSemester = () => {
  
};

const AddSemester = () => (
  <ThemeProvider theme={theme}>
    <Card className="add-semester">
      <CardContent>
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            flexDirection: "row",
          }}
        >
          <Typography color="primary">Adicionar semestre</Typography>
          <AddIcon color="secondary"/>
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

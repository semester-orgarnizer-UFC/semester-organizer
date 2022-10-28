import React from "react";
import PropTypes from "prop-types";
import "./classes-details.css";
import { Box, ThemeProvider, Rating, Typography } from "@mui/material";
import QueryBuilderIcon from "@mui/icons-material/QueryBuilder";
import SubjectIcon from "@mui/icons-material/Subject";
import StarRateIcon from "@mui/icons-material/StarRate";
import CheckIcon from "@mui/icons-material/Check";
import CloseIcon from "@mui/icons-material/Close";
import InfoIcon from "@mui/icons-material/Info";
import SchoolIcon from "@mui/icons-material/School";
import { theme } from "./../../theme.js";

const ClassesDetails = () => (
  <ThemeProvider theme={theme}>
    <Box className="classes-wrap">
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          flexDirection: "column",
        }}
      >
        <div className="row">
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              gap: 2,
            }}
          >
            <InfoIcon color="primary"></InfoIcon>
            <h2 className="title">Fundamentos de programação - QXD0001 </h2>
          </Box>

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              gap: 2,
            }}
          >
            <QueryBuilderIcon color="primary" />
            <h2>64h</h2>
          </Box>
        </div>
      </Box>
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
        }}
      >
        <Rating
          name="half-rating"
          size="large"
          readOnly
          defaultValue={2.5}
          precision={0.5}
          emptyIcon={
            <StarRateIcon style={{ opacity: 0.55 }} fontSize="inherit" />
          }
        />
        <div className="row">
          <CheckIcon color="success"></CheckIcon>
          <h3>
            Esta cadeira possui uma boa nota em relação as outras em seu curso
          </h3>
          {/* <CloseIcon sx={{
            color: "#d4452f"
          }}></CloseIcon> */}
        </div>
      </Box>

      <h3>Você cursou essa disciplina no 1ª semestre</h3>
      <div className="teachers">
        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            gap: 2,
          }}
        >
          <SchoolIcon color="primary" />
          <h2>professores: </h2>
        </Box>
        <a>Wladimir Soares</a>
        <a>David Sena</a>
        <a>Artur araruna</a>
      </div>

      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          gap: 2,
        }}
      >
        <SubjectIcon color="primary"></SubjectIcon>
        <h2>Avaliações</h2>
      </Box>

      <Box className="feedback-wrap">
        
      </Box>
    </Box>
  </ThemeProvider>
);

ClassesDetails.propTypes = {};

ClassesDetails.defaultProps = {};

export default ClassesDetails;

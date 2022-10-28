import React from "react";
import PropTypes from "prop-types";
import { Box, ThemeProvider, Rating } from "@mui/material";
import StarRateIcon from "@mui/icons-material/StarRate";


import { theme } from "./../../../theme.js";

import "./feedback-item.css";

const FeedbackItem = () => (
  <ThemeProvider theme={theme}>
    <Box className="feedback">
      <div
        sx={{
          display: "flex",
          flexDirection: "column",
        }}
      >
        <h2 className="feedback-name">Renê Júnior</h2>
        <h2 className="feedback-course">Ciência da computação</h2>
        <Rating
          name="half-rating"
          size="medium"
          readOnly
          defaultValue={2.5}
          precision={0.5}
          emptyIcon={
            <StarRateIcon style={{ opacity: 0.55 }} fontSize="inherit" />
          }
        />
      </div>
      <h3>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum id
        augue et orci hendrerit vestibulum. Aenean scelerisque eu quam imperdiet
        lobortis. Lorem ipsum dolor sit amet, consectetur morbi.
      </h3>
    </Box>
  </ThemeProvider>
);

FeedbackItem.propTypes = {};

FeedbackItem.defaultProps = {};

export default FeedbackItem;

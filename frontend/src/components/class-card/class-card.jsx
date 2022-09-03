import React from "react";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { Box } from "@mui/material";
import { Card, CardContent, Typography } from "@mui/material";

export function ClassCard(props) {
  return (
    <ThemeProvider theme={theme}>
      <Card
        sx={{
          background: "#F1DAC4",
        }}
      >
        <CardContent>
          <Typography sx={{ fontSize: 16 }} color="primary">
            {props.name}
          </Typography>
          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              mt: 1,
            }}
          >
            <Typography color="secondary" sx={{ fontSize: 14 }}>
              COD: {props.id}
            </Typography>
            <Box
              sx={{
                display: "flex",
                alignItems: "center",
              }}
            >
              <Typography
                variant="h5"
                sx={{ fontSize: 14, ml: 1 }}
                color="secondary"
              >
                {props.hours}h
              </Typography>
            </Box>
          </Box>
        </CardContent>
      </Card>
    </ThemeProvider>
  );
}

export default ClassCard;

import React from "react";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { Box } from "@mui/material";
import {
  Card,
  CardContent,
  Typography,
  Tooltip,
  IconButton,
  Menu,
  MenuItem,
} from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import DeleteIcon from "@mui/icons-material/Delete";

export function ClassCard(props) {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  const menu = (
    <Menu
      anchorEl={anchorEl}
      id="account-menu"
      open={open}
      onClose={handleClose}
      onClick={handleClose}
    >
      <MenuItem>Remover</MenuItem>
      <MenuItem>Pré-requisitos</MenuItem>
      <MenuItem>Pós-requisitos</MenuItem>
    </Menu>
  );

  return (
    <ThemeProvider theme={theme}>
      <Card>
        <CardContent>
          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
            }}
          >
            <Typography sx={{ fontSize: 16 }} color="primary">
              {props.name}
            </Typography>
            <Tooltip title="Opções">
              <IconButton
                onClick={handleClick}
                size="small"
                aria-controls={open ? `account-menu-${props.id}` : undefined}
                aria-haspopup="true"
                aria-expanded={open ? "true" : undefined}
              >
                <MoreHorizIcon color="primary" />
              </IconButton>
            </Tooltip>
            {menu}
          </Box>
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

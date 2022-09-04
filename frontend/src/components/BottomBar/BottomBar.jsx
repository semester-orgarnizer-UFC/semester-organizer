import React from "react";
import PropTypes from "prop-types";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import FolderIcon from "@mui/icons-material/Folder";
import RestoreIcon from "@mui/icons-material/Restore";
import FavoriteIcon from "@mui/icons-material/Favorite";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import { ThemeProvider } from "@emotion/react";
import { theme } from "./../../theme.js";
import LogoutIcon from "@mui/icons-material/Logout";
import PersonIcon from "@mui/icons-material/Person";
import GitHubIcon from "@mui/icons-material/GitHub";
import SchoolIcon from "@mui/icons-material/School";
import { useNavigate } from "react-router-dom";

export default function BottomBar() {
  const [value, setValue] = React.useState("/dashboard");
  const navigate = useNavigate();

  const handleChange = (event, newValue) => {
    if (newValue === "github")
      return window.open("https://github.com/semester-orgarnizer-UFC");
    setValue(newValue);

    navigate(newValue);
  };

  return (
    <ThemeProvider theme={theme}>
      <BottomNavigation
        sx={{
          position: "fixed",
          bottom: 0,
          left: 0,
          right: 0,
          background: "var(--card)",
        }}
        value={value}
        onChange={handleChange}
      >
        <BottomNavigationAction
          value="/dashboard"
          icon={<SchoolIcon />}
        />
        <BottomNavigationAction
          value="github"
          icon={<GitHubIcon />}
        />
        <BottomNavigationAction
          value="profile"
          icon={<PersonIcon />}
        />
        <BottomNavigationAction
          value="/login"
          icon={<LogoutIcon />}
        />
      </BottomNavigation>
    </ThemeProvider>
  );
}

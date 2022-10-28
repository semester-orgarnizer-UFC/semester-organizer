import React, { useState } from "react";
import { theme } from "./../../theme.js";
import {
  Card,
  CardContent,
  Typography,
  Tooltip,
  IconButton,
  Menu,
  MenuItem,
  Modal,
} from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { useDrag } from "react-dnd";
import "./classes-details.css";
import { Box, ThemeProvider, Rating } from "@mui/material";
import QueryBuilderIcon from "@mui/icons-material/QueryBuilder";
import SubjectIcon from "@mui/icons-material/Subject";
import StarRateIcon from "@mui/icons-material/StarRate";
import CheckIcon from "@mui/icons-material/Check";
import CloseIcon from "@mui/icons-material/Close";
import InfoIcon from "@mui/icons-material/Info";
import SchoolIcon from "@mui/icons-material/School";
import FeedbackItem from "../classes-details/feedback-item/feedback-item.jsx";

export function ClassCard(props) {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const [activeId, setActiveId] = useState();
  const [classModal, setClassModal] = useState();

  const [modalOpen, setModalOpen] = React.useState(false);
  const handleModalOpen = () => {
    console.log(props.item);
    setModalOpen(true);
  };
  const handleModalClose = () => setModalOpen(false);

  const [{ isDragging }, drag] = useDrag(() => ({
    type: "Card",
    item: props.item,
    collect: (monitor) => ({
      isDragging: !!monitor.isDragging(),
    }),
  }));

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
    event.preventDefault();
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
      <MenuItem onClick={handleModalOpen}>Detalhes</MenuItem>
      <MenuItem>Remover</MenuItem>
      <MenuItem>Pré-requisitos</MenuItem>
      <MenuItem>Pós-requisitos</MenuItem>
    </Menu>
  );

  return (
    <ThemeProvider theme={theme}>
      <Card ref={drag} className="class-card" onContextMenu={handleClick}>
        <CardContent>
          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
            }}
          >
            <Typography sx={{ fontSize: 16 }} color="primary">
              {props.item.name}
            </Typography>
            <Tooltip title="Opções">
              <IconButton
                onClick={handleClick}
                size="small"
                aria-controls={
                  open ? `account-menu-${props.item.id}` : undefined
                }
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
              COD: {props.item.id}
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
                {props.item.hours}h
              </Typography>
            </Box>
          </Box>
        </CardContent>
      </Card>

      <Modal open={modalOpen} onClose={handleModalClose}>
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
                <h2 className="title">
                  {props.item.name} - {props.item.id}{" "}
                </h2>
              </Box>

              <Box
                sx={{
                  display: "flex",
                  alignItems: "center",
                  gap: 2,
                }}
              >
                <QueryBuilderIcon color="primary" />
                <h2>{props.item.hours}h</h2>
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
                Esta cadeira possui uma boa nota em relação as outras em seu
                curso
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
            <a>David Sena</a>
            <a>Paulo Henrique</a>
          </div>

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              gap: 2,
            }}
          >
            <SubjectIcon color="primary"></SubjectIcon>
            <h2>Feedbacks</h2>
          </Box>

          <Box className="feedback-wrap">
            <FeedbackItem></FeedbackItem>
            <FeedbackItem></FeedbackItem>
            <FeedbackItem></FeedbackItem>
            <FeedbackItem></FeedbackItem>
            <FeedbackItem></FeedbackItem>
          </Box>
        </Box>
      </Modal>
    </ThemeProvider>
  );
}

export default ClassCard;

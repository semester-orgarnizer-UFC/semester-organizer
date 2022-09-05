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
  TextField,
} from "@mui/material";
import "./classes-not-taken.css";
import ClassCard from "../class-card/class-card.jsx";
import SearchIcon from "@mui/icons-material/Search";

const data = [
  {
    id: "QXD0001",
    name: "Fundamentos de programação",
    hours: 96,
    semester: 1,
  },
  {
    id: "QXD0108",
    name: "Introdução à ciência da computação",
    hours: 64,
    semester: 1,
  },
  {
    id: "QXD005",
    name: "Arquitetura de computadores",
    hours: 64,
    semester: 1,
  },
  {
    id: "QXD0056",
    name: "Matemática básica",
    hours: 64,
    semester: 1,
  },
  {
    id: "QXD0103",
    name: "Ética, direto e legislação",
    hours: 32,
    semester: 1,
  },
  {
    id: "QXD0007",
    name: "Programção orientada a objetos",
    hours: 64,
    semester: 2,
    preRequisite: {
      ref: "Classes",
      id: "QXD0001",
    },
  },
  {
    id: "QXD0010",
    name: "Estrutura de dados",
    hours: 64,
    semester: 2,
    preRequisite: {
      ref: "Classes",
      id: "QXD0001",
    },
  },
  {
    id: "QXD0008",
    name: "Matemática discreta",
    hours: 64,
    semester: 2,
    preRequisite: {
      ref: "Classes",
      id: "QXD0056",
    },
  },
  {
    id: "QXD0013",
    name: "Sistemas operacionais",
    hours: 64,
    semester: 2,
    preRequisite: {
      ref: "Classes",
      id: "QXD005",
    },
  },
  {
    id: "QXD0114",
    name: "Programação funcional",
    hours: 64,
    semester: 3,
  },
  {
    id: "QXD0115",
    name: "Estrutura de dados avançada",
    hours: 64,
    semester: 3,
    preRequisite: {
      ref: "Classes",
      id: "QXD0010",
    },
  },
  {
    id: "QXD0040",
    name: "Linguagens formais e automâtos",
    hours: 64,
    semester: 3,
    preRequisite: {
      ref: "Classes",
      id: "QXD0008",
    },
  },
  {
    id: "QXD0014",
    name: "Lógica para computação",
    hours: 64,
    semester: 3,
    preRequisite: {
      ref: "Classes",
      id: "QXD0008",
    },
  },
  {
    id: "QXD0012",
    name: "Probabilidade e estatística",
    hours: 64,
    semester: 3,
    preRequisite: {
      ref: "Classes",
      id: "QXD0056",
    },
  },
  {
    id: "QXD0016",
    name: "Linguagens de programação",
    hours: 64,
    semester: 4,
    preRequisite: {
      ref: "Classes",
      id: "QXD0007",
    },
  },
  {
    id: "QXD0041",
    name: "Projeto e análise de algoritmos",
    hours: 64,
    semester: 4,
    preRequisite: {
      ref: "Classes",
      id: "QXD0010",
    },
  },
  {
    id: "QXD0011",
    name: "Fundamento de banco de dados",
    hours: 64,
    semester: 4,
  },
  {
    id: "QXD0014",
    name: "Análise e projeto de sistemas",
    hours: 64,
    semester: 4,
    preRequisite: {
      ref: "Classes",
      id: "QXD0007",
    },
  },
  {
    id: "QXD0116",
    name: "Álgrebra linear",
    hours: 64,
    semester: 4,
  },
  {
    id: "QXD0020",
    name: "Desenvolvimento de software para web",
    hours: 64,
    semester: 5,
    preRequisite: {
      ref: "Classes",
      id: "QXD0007",
    },
  },
  {
    id: "QXD0119",
    name: "Computação gráfica",
    hours: 64,
    semester: 5,
    preRequisite: {
      ref: "Classes",
      id: "QXD0116",
    },
  },
  {
    id: "QXD0120",
    name: "Matemática computacional",
    hours: 64,
    semester: 5,
    preRequisite: {
      ref: "Classes",
      id: "QXD0116",
    },
  },
  {
    id: "QXD0025",
    name: "Compiladores",
    hours: 64,
    semester: 5,
    preRequisite: {
      ref: "Classes",
      id: "QXD0040",
    },
  },
  {
    id: "QXD0021",
    name: "Redes de computadores",
    hours: 64,
    semester: 5,
  },
];

function ClassesNotTaken() {
  const [searchInput, setSearchInput] = useState("");
  const [filteredResults, setFilteredResults] = useState();

  const searchItems = (searchValue) => {
    setSearchInput(searchValue);

    const filteredItems = data.filter(({ name }) =>
      name.toLowerCase().includes(searchInput.toLowerCase())
    );
    setFilteredResults(filteredItems);
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
          maxHeight: 500
        }}
      >
        <CardContent>
          <Input
            onChange={(e) => searchItems(e.target.value)}
            fullWidth
            variant="filled"
            color="primary"
            id="input-with-icon-adornment"
            placeholder="Pesquisar..."
            sx={{
              color: "var(--primary)"
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
            {searchInput.length > 1
              ? filteredResults.map((item, index) => (
                  <ClassCard
                    name={item.name}
                    id={item.id}
                    hours={item.hours}
                    key={index}
                  ></ClassCard>
                ))
              : data.map((item, index) => (
                  <ClassCard
                    name={item.name}
                    id={item.id}
                    hours={item.hours}
                    key={index}
                  ></ClassCard>
                ))}
          </Box>
        </CardContent>
      </Card>
    </ThemeProvider>
  );
}

ClassesNotTaken.propTypes = {};

ClassesNotTaken.defaultProps = {};

export default ClassesNotTaken;

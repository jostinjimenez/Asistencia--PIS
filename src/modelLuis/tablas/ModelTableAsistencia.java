package modelLuis.tablas;

import javax.swing.table.AbstractTableModel;

import model.Asistencia;
import model.Persona;

import modelLuis.controller.ControllerAsistencia;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModelTableAsistencia extends AbstractTableModel {

    private ControllerAsistencia a = new ControllerAsistencia();
    private ListaEnlazada<Asistencia> Asistencias;
    private ListaEnlazada<Persona> lista = new ListaEnlazada<>();

    @Override
    public int getRowCount() {
        return getLista().getSize();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 3:
                return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (!isCellEditable(rowIndex, columnIndex)) {
            return;
        }
        switch (columnIndex) {
            case 3:
                if (aValue != null) {
                    try {
                        String faltaInput = aValue.toString().toUpperCase().trim();

                        switch (faltaInput) {
                            case "T":
                                Asistencia asistenciaT = Asistencias.get(rowIndex);
                                asistenciaT.setEstado_asistencia("ASISTIO");
                                fireTableCellUpdated(rowIndex, columnIndex);
                                break;
                            case "J":
                                Asistencia asistenciaJ = Asistencias.get(rowIndex);
                                asistenciaJ.setEstado_asistencia("JUSTIFICADA");
                                fireTableCellUpdated(rowIndex, columnIndex);
                                break;
                            case "I":
                                Asistencia asistenciaI = Asistencias.get(rowIndex);
                                asistenciaI.setEstado_asistencia("INJUSTIFICADA");
                                fireTableCellUpdated(rowIndex, columnIndex);
                                break;
                            default:
                                break;
                        }
                    } catch (VacioExceptions ex) {
                        System.out.println("ERROR" + ex);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        Persona persona = null;
        Asistencia asistencia = null;
        try {
            persona = getLista().get(row);
            if (getAsistencias() == null) {
            } else {
                asistencia = getAsistencias().get(row);
            }
        } catch (VacioExceptions e) {
//            throw new RuntimeException(e);
        }
        if (persona != null) {
            switch (col) {
                case 0:
                    return persona.getNombre();
                case 1:
                    return persona.getApellido();
                case 2:
                    return persona.getDni();
                case 3:
                    if (asistencia != null) {
                        return asistencia.getEstado_asistencia();
                    } else {
                        return "ASISTIO";
                    }
                case 4:
                    if (asistencia != null) {
                        return asistencia.getId();
                    } else {
                        return 0;
                    }
                case 5:
                    if (asistencia != null) {
                        return asistencia.getCursa_id();
                    } else {
                        return 0;
                    }
                default:

                    return null;
            }
        } else {
            return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {

            case 0:
                return "Nombre";

            case 1:
                return "Apellido";
            case 2:
                return "DNI";
            case 3:
                return "Falta";
            case 4:
                return "ID";
            case 5:
                return "ID_CURSA";
            default:
                return null;
        }
    }

    public ListaEnlazada<Asistencia> getAsistencias() {
        return Asistencias;
    }

    public void setAsistencias(ListaEnlazada<Asistencia> Asistencias) {
        this.Asistencias = Asistencias;
    }

    public ListaEnlazada<Persona> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Persona> lista) {
        this.lista = lista;
    }

}

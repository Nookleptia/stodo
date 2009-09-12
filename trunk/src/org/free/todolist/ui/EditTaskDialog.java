package org.free.todolist.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.free.todolist.ListMainFrame;
import org.free.todolist.data.DataKit;
import org.free.todolist.model.TodoItem;

public class EditTaskDialog extends javax.swing.JDialog{
	private static final long serialVersionUID = 6661300492971491836L;
	
	private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox cboxStatus;
    private javax.swing.JComboBox cboxType;
    private javax.swing.JEditorPane epNote;
    private javax.swing.JLabel labDesc;
    private javax.swing.JLabel labNote;
    private javax.swing.JLabel labPeriod;
    private javax.swing.JLabel labStatus;
    private javax.swing.JLabel labTimeout;
    private javax.swing.JLabel labType;
    private javax.swing.JPanel panelController;
    private javax.swing.JPanel panelEditor;
    private javax.swing.JScrollPane spNote;
    private javax.swing.JTextField tfDesc;
    private javax.swing.JTextField tfPeriod;
    private javax.swing.JTextField tfTimeout;
	
	private ListMainFrame parent;
	private TodoItem currentItem;
	
	public EditTaskDialog(ListMainFrame parent, String title, TodoItem item) {
		super(parent, title, true);
		this.parent = parent;
		this.currentItem = item;
		initComponents();
	}
	
	private void initComponents(){
        panelEditor = new javax.swing.JPanel();
        labDesc = new javax.swing.JLabel();
        tfDesc = new javax.swing.JTextField();
        labType = new javax.swing.JLabel();
        cboxType = new javax.swing.JComboBox();
        labTimeout = new javax.swing.JLabel();
        tfTimeout = new javax.swing.JTextField();
        labPeriod = new javax.swing.JLabel();
        tfPeriod = new javax.swing.JTextField();
        labStatus = new javax.swing.JLabel();
        cboxStatus = new javax.swing.JComboBox();
        labNote = new javax.swing.JLabel();
        spNote = new javax.swing.JScrollPane();
        epNote = new javax.swing.JEditorPane();
        panelController = new javax.swing.JPanel();
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        panelEditor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        panelEditor.setName("panelEditor"); // NOI18N

        labDesc.setText("Description"); // NOI18N
        labDesc.setName("labDesc"); // NOI18N

        //tfDesc.setText("This is a short description"); // NOI18N
        tfDesc.setName("tfDesc"); // NOI18N

        labType.setText("Type:"); // NOI18N
        labType.setName("labType"); // NOI18N

        cboxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "node", "root" }));
        cboxType.setName("cboxType"); // NOI18N

        labTimeout.setText("Timeout:"); // NOI18N
        labTimeout.setName("labTimeout"); // NOI18N

        //tfTimeout.setText("4pm"); // NOI18N
        tfTimeout.setName("tfTimeout"); // NOI18N

        labPeriod.setText("Period:"); // NOI18N
        labPeriod.setName("labPeriod"); // NOI18N

        //tfPeriod.setText("every Sunday afternoon"); // NOI18N
        tfPeriod.setName("tfPeriod"); // NOI18N

        labStatus.setText("Status"); // NOI18N
        labStatus.setName("labStatus"); // NOI18N

        cboxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "new", "pending", "finished", "canceled" }));
        cboxStatus.setName("cboxStatus"); // NOI18N

        labNote.setText("Note:"); // NOI18N
        labNote.setName("labNote"); // NOI18N

        spNote.setName("spNote"); // NOI18N

        //epNote.setBackground(); // NOI18N//
        epNote.setName("epNote"); // NOI18N
        spNote.setViewportView(epNote);

        javax.swing.GroupLayout panelEditorLayout = new javax.swing.GroupLayout(panelEditor);
        panelEditor.setLayout(panelEditorLayout);
        panelEditorLayout.setHorizontalGroup(
            panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labDesc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labType, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labTimeout, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labNote, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDesc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEditorLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spNote, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                            .addGroup(panelEditorLayout.createSequentialGroup()
                                .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfTimeout)
                                    .addComponent(cboxType, 0, 117, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labPeriod, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labStatus, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfPeriod, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addGap(27, 27, 27))
        );
        panelEditorLayout.setVerticalGroup(
            panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labDesc)
                    .addComponent(tfDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labType)
                    .addComponent(cboxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labStatus))
                .addGap(18, 18, 18)
                .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTimeout)
                    .addComponent(tfTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labPeriod))
                .addGap(26, 26, 26)
                .addGroup(panelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labNote)
                    .addComponent(spNote, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        panelController.setName("panelController"); // NOI18N

        btnSubmit.setIcon(new ImageIcon("imgs/submit.gif")); // NOI18N
        btnSubmit.setText("Submit"); // NOI18N
        btnSubmit.setName("btnSubmit"); // NOI18N

        btnCancel.setIcon(new ImageIcon("imgs/cancel.gif")); // NOI18N
        btnCancel.setText("Cancel"); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N

        
        btnSubmit.addActionListener(new ActionListener(){
        	TodoItem data = new TodoItem();
			public void actionPerformed(ActionEvent e) {
				data.setId(currentItem.getId());
				data.setDesc(tfDesc.getText());
				data.setType(cboxType.getSelectedItem().toString());
				data.setStatus(cboxStatus.getSelectedItem().toString());
				data.setTimeout(tfTimeout.getText());
				data.setPeriod(tfPeriod.getText());
				data.setNote(epNote.getText());
				
				DataKit dt = new DataKit();
				boolean s = dt.updateExistTask(data);
				if(s){
					JOptionPane.showMessageDialog(null, 
							"New task inserted", "Success", JOptionPane.INFORMATION_MESSAGE);
					EditTaskDialog.this.parent.updateList(data);
					EditTaskDialog.this.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, 
							"Something is wrong", dt.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
        	
        });
        
        btnCancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				EditTaskDialog.this.setVisible(false);
			}
        	
        });
        
        javax.swing.GroupLayout panelControllerLayout = new javax.swing.GroupLayout(panelController);
        panelController.setLayout(panelControllerLayout);
        panelControllerLayout.setHorizontalGroup(
            panelControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControllerLayout.createSequentialGroup()
                .addContainerGap(221, Short.MAX_VALUE)
                .addComponent(btnSubmit)
                .addGap(18, 18, 18)
                .addComponent(btnCancel)
                .addContainerGap())
        );
        panelControllerLayout.setVerticalGroup(
            panelControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnCancel)
                .addComponent(btnSubmit))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelController, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelController, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        
        tfDesc.setText(currentItem.getDesc());
        tfTimeout.setText(currentItem.getTimeout());
        tfPeriod.setText(currentItem.getPeriod());
        cboxType.setSelectedItem(currentItem.getType());
        cboxStatus.setSelectedItem(currentItem.getStatus());
        epNote.setText(currentItem.getNote());
        
        pack();
    
	}
}

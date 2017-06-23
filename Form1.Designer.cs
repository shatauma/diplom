namespace Diplom
{
    partial class Form1
    {
        /// <summary>
        /// Требуется переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Обязательный метод для поддержки конструктора - не изменяйте
        /// содержимое данного метода при помощи редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.Alg1Iter = new System.Windows.Forms.Button();
            this.Alg2End = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.Alg1Eps = new System.Windows.Forms.Button();
            this.Alg1End = new System.Windows.Forms.Button();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.Alg2Eps = new System.Windows.Forms.Button();
            this.InitBtn = new System.Windows.Forms.Button();
            this.Alg2Iter = new System.Windows.Forms.Button();
            this.label7 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.White;
            this.pictureBox1.Location = new System.Drawing.Point(12, 12);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(500, 500);
            this.pictureBox1.TabIndex = 0;
            this.pictureBox1.TabStop = false;
            // 
            // Alg1Iter
            // 
            this.Alg1Iter.Enabled = false;
            this.Alg1Iter.Location = new System.Drawing.Point(521, 119);
            this.Alg1Iter.Name = "Alg1Iter";
            this.Alg1Iter.Size = new System.Drawing.Size(75, 23);
            this.Alg1Iter.TabIndex = 1;
            this.Alg1Iter.Text = "Iteration";
            this.Alg1Iter.UseVisualStyleBackColor = true;
            this.Alg1Iter.Click += new System.EventHandler(this.Alg1Iter_Click);
            // 
            // Alg2End
            // 
            this.Alg2End.Enabled = false;
            this.Alg2End.Location = new System.Drawing.Point(518, 339);
            this.Alg2End.Name = "Alg2End";
            this.Alg2End.Size = new System.Drawing.Size(75, 23);
            this.Alg2End.TabIndex = 6;
            this.Alg2End.Text = "Till end";
            this.Alg2End.UseVisualStyleBackColor = true;
            this.Alg2End.Click += new System.EventHandler(this.Alg2End_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(560, 12);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(35, 13);
            this.label1.TabIndex = 8;
            this.label1.Text = "label1";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(518, 12);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(44, 13);
            this.label2.TabIndex = 10;
            this.label2.Text = "Delta = ";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(518, 92);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(94, 13);
            this.label3.TabIndex = 12;
            this.label3.Text = "Algoritm Har-Peled";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(518, 265);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(102, 13);
            this.label4.TabIndex = 13;
            this.label4.Text = "Algorithm Malandain";
            // 
            // Alg1Eps
            // 
            this.Alg1Eps.Enabled = false;
            this.Alg1Eps.Location = new System.Drawing.Point(521, 148);
            this.Alg1Eps.Name = "Alg1Eps";
            this.Alg1Eps.Size = new System.Drawing.Size(75, 23);
            this.Alg1Eps.TabIndex = 2;
            this.Alg1Eps.Text = "Till e^-6";
            this.Alg1Eps.UseVisualStyleBackColor = true;
            this.Alg1Eps.Click += new System.EventHandler(this.Alg1Eps_Click);
            // 
            // Alg1End
            // 
            this.Alg1End.Enabled = false;
            this.Alg1End.Location = new System.Drawing.Point(521, 177);
            this.Alg1End.Name = "Alg1End";
            this.Alg1End.Size = new System.Drawing.Size(75, 23);
            this.Alg1End.TabIndex = 3;
            this.Alg1End.Text = "Till end";
            this.Alg1End.UseVisualStyleBackColor = true;
            this.Alg1End.Click += new System.EventHandler(this.Alg1End_Click);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(518, 25);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(39, 13);
            this.label5.TabIndex = 11;
            this.label5.Text = "Time =";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(560, 25);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(0, 13);
            this.label6.TabIndex = 9;
            // 
            // Alg2Eps
            // 
            this.Alg2Eps.Enabled = false;
            this.Alg2Eps.Location = new System.Drawing.Point(518, 310);
            this.Alg2Eps.Name = "Alg2Eps";
            this.Alg2Eps.Size = new System.Drawing.Size(75, 23);
            this.Alg2Eps.TabIndex = 5;
            this.Alg2Eps.Text = "Till e^-6";
            this.Alg2Eps.UseVisualStyleBackColor = true;
            this.Alg2Eps.Visible = false;
            this.Alg2Eps.Click += new System.EventHandler(this.Alg2Eps_Click);
            // 
            // InitBtn
            // 
            this.InitBtn.Location = new System.Drawing.Point(521, 489);
            this.InitBtn.Name = "InitBtn";
            this.InitBtn.Size = new System.Drawing.Size(75, 23);
            this.InitBtn.TabIndex = 7;
            this.InitBtn.Text = "Initialize";
            this.InitBtn.UseVisualStyleBackColor = true;
            this.InitBtn.Click += new System.EventHandler(this.InitBtn_Click);
            // 
            // Alg2Iter
            // 
            this.Alg2Iter.Enabled = false;
            this.Alg2Iter.Location = new System.Drawing.Point(518, 281);
            this.Alg2Iter.Name = "Alg2Iter";
            this.Alg2Iter.Size = new System.Drawing.Size(75, 23);
            this.Alg2Iter.TabIndex = 4;
            this.Alg2Iter.Text = "Iteration";
            this.Alg2Iter.UseVisualStyleBackColor = true;
            this.Alg2Iter.Visible = false;
            this.Alg2Iter.Click += new System.EventHandler(this.Alg2Iter_Click);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(518, 38);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(35, 13);
            this.label7.TabIndex = 14;
            this.label7.Text = "label7";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 562);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.Alg2Iter);
            this.Controls.Add(this.InitBtn);
            this.Controls.Add(this.Alg2Eps);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.Alg1Eps);
            this.Controls.Add(this.Alg1Iter);
            this.Controls.Add(this.Alg1End);
            this.Controls.Add(this.Alg2End);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "Form1";
            this.Text = "Diameter";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button Alg1Iter;
        private System.Windows.Forms.Button Alg1Eps;
        private System.Windows.Forms.Button Alg1End;
        private System.Windows.Forms.Button Alg2End;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button Alg2Eps;
        private System.Windows.Forms.Button InitBtn;
        private System.Windows.Forms.Button Alg2Iter;
        private System.Windows.Forms.Label label7;
    }
}


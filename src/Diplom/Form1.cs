using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Diagnostics;

namespace Diplom
{
    public partial class Form1 : Form
    {
        List<PointDim> PointSet = new List<PointDim>();
        Algorithm1 Algorithm1;
        Algorithm2 Algorithm2;
        long time = 0;
        public Form1()
        {
            InitializeComponent();
            Initialize();
        }
        private void Initialize()
        {
            Alg1Iter.Enabled = false;
            Alg1Eps.Enabled = false;
            Alg1End.Enabled = false;
            Alg2Iter.Enabled = false;
            Alg2Eps.Enabled = false;
            Alg2End.Enabled = false;
            InitBtn.Enabled = false;
            this.label7.Text = "Dimensions: " + Base.DIMENSION + " Points count: " + Base.POINTS_COUNT;

            Debug.WriteLine("Started Initialize");
            Debug.WriteLine(DateTime.Now.ToString("hh.mm.ss.ffffff"));
            PointSet = new List<PointDim>();
            Base.CreateRandomPoints(PointSet);

            Debug.WriteLine(DateTime.Now.ToString("hh.mm.ss.ffffff"));
            Algorithm1 = new Algorithm1(PointSet);
            Algorithm2 = new Algorithm2(PointSet, this);

            Debug.WriteLine(DateTime.Now.ToString("hh.mm.ss.ffffff"));
            Debug.WriteLine("Finished Initialize");

            if (Base.DIMENSION == 2)
            {
                Bitmap bitmap = new Bitmap(pictureBox1.Width, pictureBox1.Height);
                Graphics gr = Graphics.FromImage(bitmap);
                OutputPointsSet(bitmap, PointSet);
                pictureBox1.Image = bitmap;
                pictureBox1.Refresh();
            }

            Alg1Iter.Enabled = true;
            Alg1Eps.Enabled = true;
            Alg1End.Enabled = true;
            Alg2Iter.Enabled = true;
            Alg2Eps.Enabled = true;
            Alg2End.Enabled = true;
        }
        public void OutputPointsSet(Bitmap bitmap, List<PointDim> PointSet)
        {
            //Debug.WriteLine("OutputPointsSet() : PointSet.Count=" + PointSet.Count);
            foreach (PointDim point in PointSet)
            {
                bitmap.SetPixel((int)((pictureBox1.Width - 1) * point.X[0]),
                    (int)((pictureBox1.Height - 1) * point.X[1]), Color.FromArgb(255, 255, 0));
            }
            //Debug.WriteLine("OutputPointsSet() : Done");
        }
        private void OutputBoundingBoxes(Graphics gr, float left, float right, float top, float bottom)
        {
            //Debug.WriteLine("OutputBoundingBoxes() : left=" + left + " top=" + top + " right=" + right + " bottom=" + bottom);
            gr.DrawRectangle(new Pen(Brushes.Green),
                (int)(Math.Min(left, right) * pictureBox1.Width),
                (int)(Math.Min(top, bottom) * pictureBox1.Height),
                (int)(Math.Abs(left - right) * pictureBox1.Width),
                (int)(Math.Abs(top - bottom) * pictureBox1.Height));
            //Debug.WriteLine("OutputBoundingBoxes() : Done");
        }
        private void OutputBoundingBoxes(Graphics gr, PointMy one, PointMy two)
        {
            OutputBoundingBoxes(gr, one.X, two.X, one.Y, two.Y);
        }
        private void OutputDiameterPoints(Graphics gr, PointDim[] DN)
        {
            gr.DrawEllipse(new Pen(Brushes.Blue, 3), DN[0].X[0] * pictureBox1.Width - 2,
                DN[0].X[1] * pictureBox1.Height - 2, 4, 4);
            gr.DrawEllipse(new Pen(Brushes.Blue, 3), DN[1].X[0] * pictureBox1.Width - 2,
                DN[1].X[1] * pictureBox1.Height - 2, 4, 4);
        }
        public void OutputDiameterPoints(PointDim[] DN)
        {
            Bitmap bitmap = new Bitmap(pictureBox1.Image);
            Graphics gr = Graphics.FromImage(bitmap);
            OutputDiameterPoints(gr, DN);
            pictureBox1.Image = bitmap;
            pictureBox1.Refresh();
        }
        private void Alg1Iter_Click(object sender, EventArgs e)
        {
            Alg1Iter.Enabled = false;
            Alg1Eps.Enabled = false;
            Alg1End.Enabled = false;
            Alg2Iter.Enabled = false;
            Alg2Eps.Enabled = false;
            Alg2End.Enabled = false;
            InitBtn.Enabled = false;

            Debug.WriteLine("Started Algorythm 1 Iteration : " + (Algorithm1.IterationCount + 1));
            time = DateTime.Now.Ticks;
            Algorithm1.Iteration();
            time = DateTime.Now.Ticks - time;
            label6.Text = (time / 10000000.0f).ToString();
            Debug.WriteLine("Time = " + time / 10000000.0f);
            Debug.WriteLine("Finished Algorythm 1 Iteration");

            if (Base.DIMENSION == 2)
            {
                Bitmap bitmap = new Bitmap(pictureBox1.Width, pictureBox1.Height);
                Graphics gr = Graphics.FromImage(bitmap);
                foreach (Node node in Algorithm1.Tree)
                {
                    OutputPointsSet(bitmap, node.PointSet);
                }
                foreach (Node node in Algorithm1.Tree)
                {
                    OutputBoundingBoxes(gr, node.left[0], node.right[0], node.left[1], node.right[1]);
                }
                pictureBox1.Image = bitmap;
                pictureBox1.Refresh();
            }
            label1.Text = Algorithm1.delta.ToString();

            Alg1Iter.Enabled = true;
            InitBtn.Enabled = true;
        }
        private void Alg1Eps_Click(object sender, EventArgs e)
        {
            Alg1Iter.Enabled = false;
            Alg1Eps.Enabled = false;
            Alg1End.Enabled = false;
            Alg2Iter.Enabled = false;
            Alg2Eps.Enabled = false;
            Alg2End.Enabled = false;
            InitBtn.Enabled = false;

            Debug.WriteLine("Started Algorythm 1 Till " + Base.epsilon);
            time = DateTime.Now.Ticks;
            while (Math.Abs(Algorithm1.maxDelta - Algorithm1.delta) > Base.epsilon)
            {
                Algorithm1.Iteration();
                //Debug.WriteLine("Algorythm 1 : " + Algorithm1.IterationCount + " : distance=" + Algorithm1.delta + " delta=" + Math.Abs(Algorithm1.maxDelta - Algorithm1.delta));
            }
            time = DateTime.Now.Ticks - time;
            label6.Text = (time / 10000000.0f).ToString();
            Debug.WriteLine("Time = " + time / 10000000.0f);
            Debug.WriteLine("Finished Algorythm 1 Till " + Base.epsilon);

            if (Base.DIMENSION == 2)
            {
                Bitmap bitmap = new Bitmap(pictureBox1.Width, pictureBox1.Height);
                Graphics gr = Graphics.FromImage(bitmap);
                foreach (Node node in Algorithm1.Tree)
                {
                    OutputPointsSet(bitmap, node.PointSet);
                }
                foreach (Node node in Algorithm1.Tree)
                {
                    OutputBoundingBoxes(gr, node.left[0], node.right[0], node.left[1], node.right[1]);
                }
                pictureBox1.Image = bitmap;
                pictureBox1.Refresh();
            }
            label1.Text = Algorithm1.delta.ToString();

            InitBtn.Enabled = true;
        }
        private void Alg1End_Click(object sender, EventArgs e)
        {
            Alg1Iter.Enabled = false;
            Alg1Eps.Enabled = false;
            Alg1End.Enabled = false;
            Alg2Iter.Enabled = false;
            Alg2Eps.Enabled = false;
            Alg2End.Enabled = false;
            InitBtn.Enabled = false;

            Debug.WriteLine("Started Algorythm 1 Till end");
            time = DateTime.Now.Ticks;
            bool stop = false;
            while (!stop)
            {
                Algorithm1.Iteration();
                if ((Algorithm1.Tree.Count == 2 && Algorithm1.Tree[0].PointSet.Count < 2
                    && Algorithm1.Tree[1].PointSet.Count < 2) || (Algorithm1.Tree.Count == 1
                    && Algorithm1.Tree[0].PointSet.Count < 3) || (Algorithm1.Tree.Count == 0))
                {
                    stop = true;
                }
                if (Base.DIMENSION == 2)
                {
                    Bitmap bitmap = new Bitmap(pictureBox1.Width, pictureBox1.Height);
                    Graphics gr = Graphics.FromImage(bitmap);
                    foreach (Node node in Algorithm1.Tree)
                    {
                        OutputPointsSet(bitmap, node.PointSet);
                    }
                    foreach (Node node in Algorithm1.Tree)
                    {
                        OutputBoundingBoxes(gr, node.left[0], node.right[0], node.left[1], node.right[1]);
                    }
                    pictureBox1.Image = bitmap;
                    pictureBox1.Refresh();
                }
                label1.Text = Algorithm1.delta.ToString();
                //Debug.WriteLine("Algorythm 1 : " + Algorithm1.IterationCount + " : distance=" + Algorithm1.delta + " delta=" + Math.Abs(Algorithm1.maxDelta - Algorithm1.delta) + " Nodes=" + Algorithm1.Tree.Count());
            }
            time = DateTime.Now.Ticks - time;
            label6.Text = (time / 10000000.0f).ToString();
            Debug.WriteLine("Time = " + time / 10000000.0f);
            Debug.WriteLine("Finished Algorythm 1 Till end" + " Point=(" + Algorithm1.Tree.ElementAt(0).PointSet.ElementAt(0).X[0] + ", " + Algorithm1.Tree.ElementAt(0).PointSet.ElementAt(0).X[1] + "), (" + Algorithm1.Tree.ElementAt(0).Pairs.ElementAt(0).PointSet.ElementAt(0).X[0] + ", " + Algorithm1.Tree.ElementAt(0).Pairs.ElementAt(0).PointSet.ElementAt(0).X[0] + ")");

            /*if (Base.DIMENSION == 2)
            {
                Bitmap bitmap = new Bitmap(pictureBox1.Width, pictureBox1.Height);
                Graphics gr = Graphics.FromImage(bitmap);
                foreach (Node node in Algorithm1.Tree)
                {
                    OutputPointsSet(bitmap, node.PointSet);
                }
                pictureBox1.Image = bitmap;
                pictureBox1.Refresh();
            }
            label1.Text = Algorithm1.delta.ToString();*/

            InitBtn.Enabled = true;
        }
        private void Alg2Iter_Click(object sender, EventArgs e)
        {
            Alg1Iter.Enabled = false;
            Alg1Eps.Enabled = false;
            Alg1End.Enabled = false;
            Alg2Iter.Enabled = false;
            Alg2Eps.Enabled = false;
            Alg2End.Enabled = false;
            InitBtn.Enabled = false;

            Debug.WriteLine("Started Algorythm 2 Iteration : " + (Algorithm1.IterationCount + 1));
            time = DateTime.Now.Ticks;
            PointDim[] DN = Algorithm2.Iteration(PointSet, 2.0f);
            time = DateTime.Now.Ticks - time;
            label6.Text = (time / 10000000.0f).ToString();
            Debug.WriteLine("Time = " + time / 10000000.0f);
            Debug.WriteLine("Finished Algorythm 2 Iteration");

            /*Bitmap bitmap = new Bitmap(pictureBox1.Width, pictureBox1.Height);
            Graphics gr = Graphics.FromImage(bitmap);
            OutputPointsSet(bitmap, PointSet);
            OutputDiameterPoints(gr, DN);
            pictureBox1.Image = bitmap;
            pictureBox1.Refresh();*/
            label1.Text = Base.Distance(DN[0], DN[1]).ToString();

            Alg2Iter.Enabled = true;
            InitBtn.Enabled = true;
        }
        private void Alg2Eps_Click(object sender, EventArgs e)
        {
            Alg1Iter.Enabled = false;
            Alg1Eps.Enabled = false;
            Alg1End.Enabled = false;
            Alg2Iter.Enabled = false;
            Alg2Eps.Enabled = false;
            Alg2End.Enabled = false;
            InitBtn.Enabled = false;

            Debug.WriteLine("Started Algorythm 2 Till " + Base.epsilon);
            time = DateTime.Now.Ticks;
            PointDim[] DN = Algorithm2.Iteration(PointSet, Base.epsilon);
            time = DateTime.Now.Ticks - time;
            label6.Text = (time / 10000000.0f).ToString();
            Debug.WriteLine("Time = " + time / 10000000.0f);
            Debug.WriteLine("Finished Algorythm 2 Till " + Base.epsilon);

            /*Bitmap bitmap = new Bitmap(pictureBox1.Width, pictureBox1.Height);
            Graphics gr = Graphics.FromImage(bitmap);
            OutputPointsSet(bitmap, PointSet);
            OutputDiameterPoints(gr, DN);
            pictureBox1.Image = bitmap;
            pictureBox1.Refresh();*/
            label1.Text = Base.Distance(DN[0], DN[1]).ToString();

            InitBtn.Enabled = true;
        }
        private void Alg2End_Click(object sender, EventArgs e)
        {
            Alg1Iter.Enabled = false;
            Alg1Eps.Enabled = false;
            Alg1End.Enabled = false;
            Alg2Iter.Enabled = false;
            Alg2Eps.Enabled = false;
            Alg2End.Enabled = false;
            InitBtn.Enabled = false;

            Debug.WriteLine("Started Algorythm 2 Till end");
            time = DateTime.Now.Ticks;
            PointDim[] DN = Algorithm2.Iteration(PointSet, 0.0f);
            time = DateTime.Now.Ticks - time;
            label6.Text = (time / 10000000.0f).ToString();
            Debug.WriteLine("Time = " + time / 10000000.0f);
            Debug.WriteLine("Finished Algorythm 2 Till end");

            /*Bitmap bitmap = new Bitmap(pictureBox1.Width, pictureBox1.Height);
            Graphics gr = Graphics.FromImage(bitmap);
            OutputPointsSet(bitmap, PointSet);
            OutputDiameterPoints(gr, DN);
            pictureBox1.Image = bitmap;
            pictureBox1.Refresh();*/
            label1.Text = Base.Distance(DN[0], DN[1]).ToString();

            InitBtn.Enabled = true;
        }
        private void InitBtn_Click(object sender, EventArgs e)
        {
            Initialize();
        }
    }
}

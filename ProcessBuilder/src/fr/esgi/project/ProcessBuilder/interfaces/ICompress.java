package fr.esgi.project.ProcessBuilder.interfaces;

import java.io.File;

public interface ICompress {
	void compress(File dest, File... src);
}

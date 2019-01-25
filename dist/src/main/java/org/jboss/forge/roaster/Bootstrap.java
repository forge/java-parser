package org.jboss.forge.roaster;

import java.util.Arrays;
import java.util.List;

/**
 * Main class to handle the command line access to roaster
 *
 */
public class Bootstrap
{
   /**
    * Handles the command line access
    * 
    * @param args the command line arguments (musn't be null)
    */
   public static void main(final String[] args)
   {
      List<String> arguments = Arrays.asList(args);

      if (arguments.isEmpty() || arguments.contains("--help") || arguments.contains("-h"))
      {
         new HelpCommand().run(arguments);
      }
      else
      {
         new FormatterCommand().run(arguments);
      }
   }
}
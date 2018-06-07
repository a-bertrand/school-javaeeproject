function generatechart(receive_data, class_name, button, max_value)
{
	d3.select(class_name).selectAll('svg').remove();
	generate_pie(receive_data,class_name,max_value)
	var onclick = true;
	
	d3.select(button).text('Voir Graphique en Barre');

	d3.select(button).on('click',function()
	{
		if(onclick == false)
		{
			d3.select(class_name).selectAll('svg').remove();
			generate_pie(receive_data,class_name,max_value)
			onclick = true;
			
			d3.select(button).text('Voir Graphique en Barre');
		}
		else
		{
			d3.select(class_name).selectAll('svg').remove();
			generate_bar(receive_data,class_name,max_value)
			onclick = false;
			d3.select(button).text('Voir Graphique en "Pie"');
		}	
	})
	;
}

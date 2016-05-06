package br.octa.enums;

public class EasterEgg {


	public void showLogo(String REQUTEXT){
		StringBuilder messageSTFConnection = new StringBuilder();
		messageSTFConnection.append("#########################################################################################################################################################\n");
		messageSTFConnection.append("#    {____         {__   {___ {______      {_              {__ __      {____     {__      {__     {__{___ {______{__    {____     {___     {__  {__ __  #\n");
		messageSTFConnection.append("#  {__    {__   {__   {__     {__         {_ __          {__    {__  {__    {__  {__      {__     {__     {__    {__  {__    {__  {_ {__   {__{__    {__#\n");
		messageSTFConnection.append("#{__        {__{__            {__        {_  {__          {__      {__        {__{__      {__     {__     {__    {__{__        {__{__ {__  {__ {__      #\n");
		messageSTFConnection.append("#{__        {__{__            {__       {__   {__           {__    {__        {__{__      {__     {__     {__    {__{__        {__{__  {__ {__   {__    #\n");
		messageSTFConnection.append("#{__        {__{__            {__      {______ {__             {__ {__        {__{__      {__     {__     {__    {__{__        {__{__   {_ {__      {__ #\n");
		messageSTFConnection.append("#  {__     {__  {__   {__     {__     {__       {__      {__    {__  {__     {__ {__      {__     {__     {__    {__  {__     {__ {__    {_ __{__    {__#\n");
		messageSTFConnection.append("#    {____        {____       {__    {__         {__       {__ __      {____     {________  {_____        {__    {__    {____     {__      {__  {__ __  #\n");
		messageSTFConnection.append("#########################################################################################################################################################\n");
		messageSTFConnection.append("########################################################### Copyright (c) 2003 Company Name #############################################################\n");
		messageSTFConnection.append("###########################################################     All Rights Reserved         #############################################################\n");
		messageSTFConnection.append("############################################ This product is protected by copyright and distributed under ###############################################\n");
		messageSTFConnection.append("########################################### licenses restricting copying, distribution, and decompilation. ##############################################\n");
		messageSTFConnection.append("################################################### This is test for SAP Function STFC_CONNECTION. ######################################################\n");
		messageSTFConnection.append("REQUTEXT = "+ REQUTEXT );
		System.out.println(messageSTFConnection.toString());
	}
}
